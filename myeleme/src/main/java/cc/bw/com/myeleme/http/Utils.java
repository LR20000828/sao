package cc.bw.com.myeleme.http;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {

    //Okhttp
    public static String postForm(String url,String[] name,String[] value){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (int i=0;i<name.length;i++){
            builder.add(name[i],value[i]);
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            return execute.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String get(String urlString){

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(urlString).get().build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
