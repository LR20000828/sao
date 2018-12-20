package cc.bw.com.myeleme.precenter;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cc.bw.com.myeleme.bean.Goods;
import cc.bw.com.myeleme.bean.MyBoss;
import cc.bw.com.myeleme.bean.MyData;
import cc.bw.com.myeleme.bean.Result;
import cc.bw.com.myeleme.bean.Shop;
import cc.bw.com.myeleme.code.CallBackk;
import cc.bw.com.myeleme.code.GoodsBak;
import cc.bw.com.myeleme.model.Goods_Model;

public class Goods_precenter {
    private CallBackk data;

    public Goods_precenter(CallBackk data) {
        this.data = data;
    }
    private GoodsBak goodsBak;

    public Goods_precenter(GoodsBak goodsBak) {
        this.goodsBak = goodsBak;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyBoss myBoss = (MyBoss) msg.obj;
            data.Data((ArrayList<MyData>) myBoss.getData());
        }
    };

    Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Result result = (Result) msg.obj;
            List<Shop> data = result.getData();
            goodsBak.Goodss(data);
        }
    };

    Handler handler3 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyBoss myBoss = (MyBoss) msg.obj;
            data.Data2((ArrayList<MyData>) myBoss.getData());
        }
    };

    public void presenterData(final String aa){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyBoss data = Goods_Model.getData(aa);
                Message message  = handler.obtainMessage();
                message.obj = data;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void presenterData2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result data = Goods_Model.getGoodsData();
                Message message  = handler2.obtainMessage();
                message.obj = data;
                handler2.sendMessage(message);
            }
        }).start();
    }
    public void presenterData3(final String aa){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyBoss data = Goods_Model.getData(aa);
                Message message  = handler3.obtainMessage();
                message.obj = data;
                handler3.sendMessage(message);
            }
        }).start();
    }

}
