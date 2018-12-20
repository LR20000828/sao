package cc.bw.com.myeleme.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.bw.com.myeleme.R;
import cc.bw.com.myeleme.adapter.MyGoodsAdapter;
import cc.bw.com.myeleme.bean.Shop;
import cc.bw.com.myeleme.code.GoodsBak;
import cc.bw.com.myeleme.precenter.Goods_precenter;

public class MyGoodscar extends AppCompatActivity implements GoodsBak,MyGoodsAdapter.TotalPriceListener {


    private List<Shop> sh = new ArrayList<>();
    private MyGoodsAdapter myGoodsAdapter;
    private ExpandableListView listview;
    private TextView sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goodscar);


        listview = findViewById(R.id.eListView);
        myGoodsAdapter = new MyGoodsAdapter(sh, this);
        myGoodsAdapter.setTotalPriceListener(this);
        listview.setAdapter(myGoodsAdapter);
        sum = findViewById(R.id.sum);


        Goods_precenter goods_precenter = new Goods_precenter(this);
        goods_precenter.presenterData2();
    }


    @Override
    public void totalPrice(double totalPrice) {
        sum.setText(totalPrice+"");
    }

    @Override
    public void Goodss(List<Shop> shops) {
        sh.addAll(shops);
        Toast.makeText(this,sh.get(1).getSellerName()+"",1).show();
        int groupCount = sh.size();
        for (int i = 0; i < groupCount; i++) {
            listview.expandGroup(i);
        }
        myGoodsAdapter.notifyDataSetChanged();
    }
}
