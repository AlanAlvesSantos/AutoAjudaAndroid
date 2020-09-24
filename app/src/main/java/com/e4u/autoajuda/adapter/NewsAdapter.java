package com.e4u.autoajuda.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.activities.NewsReadActivity;
import com.e4u.autoajuda.modelos.NewsModelo;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.VHItems> {

    private List<NewsModelo> arrayList = new ArrayList<>();
    private Activity mContext;

    public NewsAdapter(Activity mContext, List<NewsModelo> listGoodNews) {
        this.mContext = mContext;
        this.arrayList = listGoodNews;
    }

    @Override
    public NewsAdapter.VHItems onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_news, parent, false);
        return new NewsAdapter.VHItems(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.VHItems holder, final int position) {

        final NewsModelo model = arrayList.get(position);

        Glide.with(mContext).load(model.getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imgGoodNews);

        holder.txtNewsTitle.setText(model.getTitle());

        holder.rlGoodNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, NewsReadActivity.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("image", model.getImage());
                intent.putExtra("description", model.getDescription());
                intent.putExtra("subtitle", model.getSubtitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class VHItems extends RecyclerView.ViewHolder {
        TextView txtNewsTitle;
        ImageView imgGoodNews;
        RelativeLayout rlGoodNews;
        ProgressBar progressBar;

        public VHItems(View itemView) {
            super(itemView);
            rlGoodNews = (RelativeLayout) itemView.findViewById(R.id.rlNews);
            imgGoodNews = (ImageView) itemView.findViewById(R.id.imgGoodNews);
            txtNewsTitle = (TextView) itemView.findViewById(R.id.txtNewsTitle);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
        }
    }
}