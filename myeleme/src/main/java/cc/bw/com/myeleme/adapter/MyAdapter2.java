package cc.bw.com.myeleme.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cc.bw.com.myeleme.R;
import cc.bw.com.myeleme.view.WebView;
import cc.bw.com.myeleme.bean.MyData;
import cc.bw.com.myeleme.code.Myonc;


public class MyAdapter2 extends RecyclerView.Adapter {


    private ArrayList<MyData> mdata;
    private Context context;
    private MyHolder myHolder;
    private Boolean myIf;
    private Myonc myonc;

    public MyAdapter2(ArrayList<MyData> mdata, Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    public void backName(Boolean a) {
        myIf = a;
    }

    @Override
    public int getItemViewType(int position) {
        return position%mdata.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv;
        int itemViewType = getItemViewType(viewType);
        if (itemViewType == 0) {
            vv = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        } else {
            vv = LayoutInflater.from(context).inflate(R.layout.itemt_layout, parent, false);
        }
        myHolder = new MyHolder(vv);
        return myHolder;
    }

    public void addll(ArrayList<MyData> data) {
        mdata.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == 0){
            String images = mdata.get(position).getImages();
            String[] split = images.split("\\|");
            Glide.with(context).load(split[0]).into(myHolder.image);
            myHolder.tvone.setText(mdata.get(position).getTitle());
            myHolder.tvtwo.setText(mdata.get(position).getCreatetime());
            myHolder.tvthree.setText(mdata.get(position).getSubhead());
        }else{
            String images = mdata.get(position).getImages();
            String[] split = images.split("\\|");
            Glide.with(context).load(split[0]).into(myHolder.images);
            myHolder.tvones.setText(mdata.get(position).getTitle());
        }

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("image",mdata.get(position).getImages());
                intent.putExtra("price",mdata.get(position).getBargainPrice());
                intent.putExtra("title",mdata.get(position).getTitle());
                intent.putExtra("sub",mdata.get(position).getSubhead());
                context.startActivity(intent);
            }
        });
        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
                alpha.setDuration(1000);//设置动画时间
                /*alpha.setInterpolator(new DecelerateInterpolator());*///设置动画插入器，减速
               // alpha.setRepeatCount(1);//设置动画重复次数，这里-1代表无限
                alpha.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mdata.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }

                    @Override
                    public void onAnimationPause(Animator animation) {
                        super.onAnimationPause(animation);
                    }

                    @Override
                    public void onAnimationResume(Animator animation) {
                        super.onAnimationResume(animation);
                    }
                });
                alpha.start();
                return true;
            }
        });
    }

    public void clearData() {
        mdata.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tvone;
        private final TextView tvones;
        private final TextView tvtwo;
        private final TextView tvthree;
        private final ImageView image;
        private final ImageView images;

        public MyHolder(View itemView) {
            super(itemView);

            tvone = itemView.findViewById(R.id.tvone);
            tvtwo = itemView.findViewById(R.id.tvtwo);
            tvthree = itemView.findViewById(R.id.tvthree);
            image = itemView.findViewById(R.id.image);
            images = itemView.findViewById(R.id.images);
            tvones = itemView.findViewById(R.id.tvones);

        }
    }


}
