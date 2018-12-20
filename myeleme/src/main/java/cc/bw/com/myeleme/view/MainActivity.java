package cc.bw.com.myeleme.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import cc.bw.com.myeleme.adapter.MyAdapter;
import cc.bw.com.myeleme.adapter.MyAdapter2;
import cc.bw.com.myeleme.R;
import cc.bw.com.myeleme.bean.MyData;
import cc.bw.com.myeleme.code.CallBackk;
import cc.bw.com.myeleme.precenter.Goods_precenter;

public class MainActivity extends AppCompatActivity implements CallBackk,View.OnClickListener{

    private XRecyclerView xrecyclerview;
    private MyAdapter myAdapter;
    private int page=1;
    private MyAdapter2 myAdapter2;
    private String trim="笔记本";
    private ArrayList<MyData> data = new ArrayList<>();
    private Boolean aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mButtonone).setOnClickListener(this);
        findViewById(R.id.mButtontwo).setOnClickListener(this);
        findViewById(R.id.goodsButton).setOnClickListener(this);
        findViewById(R.id.goodsButton2).setOnClickListener(this);

        myAdapter2 = new MyAdapter2(data,this);

        xrecyclerview = findViewById(R.id.xrecyclerview);
        xrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        myAdapter = new MyAdapter(data,this);
        xrecyclerview.setAdapter(myAdapter);
        Goods_precenter goods_model = new Goods_precenter(this);
        goods_model.presenterData("手机");
        aa=false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.mButtontwo){
            if(aa){
                myAdapter = new MyAdapter(data,this);
                xrecyclerview.setAdapter(myAdapter);
                xrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                aa=false;
            }else{
                myAdapter2 = new MyAdapter2(data,this);
                xrecyclerview.setAdapter(myAdapter2);
                xrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                aa=true;
            }
        }else if(view.getId() == R.id.mButtonone){
            EditText text = findViewById(R.id.ediText);
            String trim = text.getText().toString().trim();
            Goods_precenter goods_model = new Goods_precenter(this);
            goods_model.presenterData3(trim);
        }else if(view.getId() == R.id.goodsButton){
            startActivity(new Intent(this,MyGoodscar.class));
            finish();
        }else if(view.getId() == R.id.goodsButton2){
            startActivity(new Intent(this,ElMa.class));
            finish();
        }
    }


    @Override
    public void Data(ArrayList<MyData> myData) {
        data.addAll(myData);
        myAdapter.addll(myData);
        myAdapter2.addll(myData);
        myAdapter.notifyDataSetChanged();
        myAdapter2.notifyDataSetChanged();
    }

    @Override
    public void Data2(ArrayList<MyData> myData) {
        data.clear();
        data.addAll(myData);
        myAdapter.notifyDataSetChanged();
        myAdapter2.notifyDataSetChanged();
    }
}
