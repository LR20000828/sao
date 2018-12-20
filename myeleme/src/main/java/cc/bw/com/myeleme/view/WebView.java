package cc.bw.com.myeleme.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cc.bw.com.myeleme.R;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String price = intent.getStringExtra("price");
        String title = intent.getStringExtra("title");
        String sub = intent.getStringExtra("sub");
        TextView title1 = findViewById(R.id.title1);
        title1.setText(title);
        TextView sub1 = findViewById(R.id.sub);
        sub1.setText(sub);
        TextView price1 = findViewById(R.id.price);
        price1.setText("￥"+price);

        Banner banner = findViewById(R.id.banner);

        List<String> list = new ArrayList<String>();

        String[] split = image.split("\\|");
        for (int i = 0; i < split.length; i++) {
            String icon = split[i].replace("https","http");
            list.add(icon);
        }
        Toast.makeText(this,image,1).show();


        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(WebView.this).load(path).into(imageView);
            }
        });
        banner.start();

    }
}
