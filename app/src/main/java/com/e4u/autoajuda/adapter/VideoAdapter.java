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
import com.e4u.autoajuda.activities.VideoPlayerActivity;
import com.e4u.autoajuda.modelos.VideoModelo;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VHItems> {

    private List<VideoModelo> arrayList = new ArrayList<>();
    private Activity mContext;
    String urlImageVideo1 = "https://img.youtube.com/vi/";
    String urlImageVideo2 = "/mqdefault.jpg";



    public VideoAdapter(Activity mContext, List<VideoModelo> listVideos) {
        this.mContext = mContext;
        this.arrayList = listVideos;
    }

    @Override
    public VideoAdapter.VHItems onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_video, parent, false);
        return new VideoAdapter.VHItems(view);
    }

    @Override
    public void onBindViewHolder(final VideoAdapter.VHItems holder, final int position) {

        final VideoModelo model = arrayList.get(position);
        holder.txtTitle.setText(model.getVideoTitle());

        Glide.with(mContext).load(urlImageVideo1 + model.getVideoURL() + urlImageVideo2)
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
                .into(holder.imgvideo);

        holder.rlVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                intent.putExtra("id", model.getVideoURL());
                intent.putExtra("title", model.getVideoTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class VHItems extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RelativeLayout rlVideo;
        ImageView imgvideo;
        ProgressBar progressBar;

        public VHItems(View itemView) {
            super(itemView);
            rlVideo = itemView.findViewById(R.id.rlVideo);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgvideo = itemView.findViewById(R.id.imgvideo);
            progressBar = itemView.findViewById(R.id.progress_load_photo);

        }
    }
}