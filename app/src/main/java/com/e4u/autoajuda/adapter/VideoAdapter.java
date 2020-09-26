package com.e4u.autoajuda.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.activities.VideoPlayerActivity;
import com.e4u.autoajuda.modelos.VideoModelo;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VHItems> {

    private List<VideoModelo> arrayList = new ArrayList<>();
    private Activity mContext;

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

        public VHItems(View itemView) {
            super(itemView);
            rlVideo = (RelativeLayout) itemView.findViewById(R.id.rlVideo);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}