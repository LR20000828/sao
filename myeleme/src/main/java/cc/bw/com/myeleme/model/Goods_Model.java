package cc.bw.com.myeleme.model;

import com.google.gson.Gson;

import cc.bw.com.myeleme.bean.MyBoss;
import cc.bw.com.myeleme.bean.Result;
import cc.bw.com.myeleme.http.Utils;

public class Goods_Model {

    public static MyBoss getData(String aa){
        String s = Utils.get("http://www.zhaoapi.cn/product/searchProducts?keywords="+aa);
        Gson gson = new Gson();
        MyBoss a = gson.fromJson(s, MyBoss.class);
        return a;
    }
    public static Result getGoodsData(){
        String s = Utils.get("http://www.zhaoapi.cn/product/getCarts?uid=71");
        Gson gson = new Gson();
        Result a = gson.fromJson(s, Result.class);
        return a;
    }
}
