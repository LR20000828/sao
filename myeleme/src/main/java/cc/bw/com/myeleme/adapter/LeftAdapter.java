package cc.bw.com.myeleme.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import cc.bw.com.myeleme.R;
import cc.bw.com.myeleme.bean.Shop;

/**
 * @author dingtao
 * @date 2018/12/19 10:56
 * qq:1940870847
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyHolder> {

    private List<Shop> mList = new ArrayList<>();

    public void addAll(List<Shop> list){
        mList.addAll(list);
    }
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.recycler_left_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final Shop shop = mList.get(i);
        myHolder.text.setText(shop.getSellerName());
        myHolder.text.setBackgroundResource(shop.getBackground());
        myHolder.text.setTextColor(shop.getTextColor());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int j = 0; j <mList.size() ; j++) {
                    mList.get(j).setTextColor(0xffffffff);
                    mList.get(j).setBackground(R.color.grayblack);
                }
                shop.setBackground(R.color.white);
                shop.setTextColor(0xff000000);
                notifyDataSetChanged();
                onItemClickListenter.onItemClick(shop);//切换右边的列表
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Shop> getList() {
        return mList;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView text;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.left_text);
        }
    }

    private OnItemClickListenter onItemClickListenter;

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    public interface OnItemClickListenter{
        void onItemClick(Shop shop);
    }

}
