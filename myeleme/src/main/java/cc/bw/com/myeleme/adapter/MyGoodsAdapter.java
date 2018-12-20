package cc.bw.com.myeleme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cc.bw.com.myeleme.R;
import cc.bw.com.myeleme.bean.Goods;
import cc.bw.com.myeleme.bean.Shop;
import cc.bw.com.myeleme.code.AddSubLayout;

public class MyGoodsAdapter extends BaseExpandableListAdapter {

    private List<Shop> shops;
    private Context context;

    public MyGoodsAdapter(List<Shop> shops, Context context) {
        this.shops = shops;
        this.context = context;
    }

    private TotalPriceListener totalPriceListener;

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }

    @Override
    public int getGroupCount() {
        return shops.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return shops.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return shops.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return shops.get(i).getList().get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHodler holder;
        if (view == null) {
            holder = new GroupHodler();
            view = View.inflate(context, R.layout.group_layout, null);
            holder.checkBox = view.findViewById(R.id.checkBox);
            view.setTag(holder);
        } else {
            holder = (GroupHodler) view.getTag();
        }
        final Shop shop = shops.get(i);
        String sellerName = shop.getSellerName();
        holder.checkBox.setText(sellerName);
        holder.checkBox.setChecked(shop.isCheck());//设置商铺选中状态
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shops.get(i).setCheck(isChecked);//数据更新
                List<Goods> goodsList = shop.getList();//得到商品信息
                for (int i = 0; i < goodsList.size(); i++) {//商品信息循环赋值
                    goodsList.get(i).setSelected(isChecked?1:0);//商铺选中则商品必须选中
                }
                notifyDataSetChanged();

                //计算价格
                calculatePrice();
            }
        });
        return view;
    }

    private void calculatePrice(){
        double totalPrice=0;
        for (int i = 0; i < shops.size(); i++) {//循环的商家
            Shop shop = shops.get(i);
            for (int j = 0; j < shop.getList().size(); j++) {
                Goods goods = shop.getList().get(j);
                if (goods.getSelected()==1) {//如果是选中状态
                    totalPrice = totalPrice + goods.getNum() * goods.getPrice();
                }
            }
        }
        if (totalPriceListener!=null)
            totalPriceListener.totalPrice(totalPrice);
    }
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {MyHolder holder;

        if (view == null) {
            view = View.inflate(context, R.layout.child_layout, null);
            holder = new MyHolder();
            holder.text = view.findViewById(R.id.goodsName);
            holder.price = view.findViewById(R.id.goodsPrice);
            holder.image = view.findViewById(R.id.goodsimage);
            holder.check = view.findViewById(R.id.ccc);
            holder.addSub = view.findViewById(R.id.add_sub_layout);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        final Goods goods = shops.get(i).getList().get(i1);
        holder.text.setText(goods.getTitle());
        holder.price.setText("单价："+goods.getPrice());//单价
        //点击选中，计算价格
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goods.setSelected(isChecked?1:0);
                calculatePrice();//计算价格
            }
        });

        if (goods.getSelected()==0){
            holder.check.setChecked(false);
        }else{
            holder.check.setChecked(true);
        }

        String imageurl = "https" + goods.getImages().split("https")[1];
        imageurl = imageurl.substring(0, imageurl.lastIndexOf(".jpg") + ".jpg".length());
        Glide.with(context).load(imageurl).into(holder.image);//加载图片

        holder.addSub.setCount(goods.getNum());//设置商品数量
        holder.addSub.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                goods.setNum(count);
                calculatePrice();//计算价格
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    class MyHolder {
        CheckBox check;
        TextView text;
        TextView price;
        ImageView image;
        AddSubLayout addSub;
    }

    class GroupHodler {
        CheckBox checkBox;
    }

    public interface TotalPriceListener{
        void totalPrice(double totalPrice);
    }

}
