package com.e4u.autoajuda.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.e4u.autoajuda.R;

public class NewsReadActivity extends AppCompatActivity {

    TextView txtTitle, txtDesc, txtSubTitle;
    ImageView imgNews;
    ProgressBar loader;
    String title, subtitle, descricao, imgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_read);

        txtTitle = findViewById(R.id.txtTitleNews);
        txtDesc = findViewById(R.id.txtDescricaoNews);
        txtSubTitle = findViewById(R.id.txtSubtitle);
        imgNews = findViewById(R.id.imgNews);
        loader = findViewById(R.id.loader);

        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        descricao = getIntent().getStringExtra("description");
        imgURL = getIntent().getStringExtra("image");

        txtSubTitle.setText(subtitle);
        txtDesc.setText(descricao);
        txtTitle.setText(title);

        Glide.with(getBaseContext()).load(imgURL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loader.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loader.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgNews);
    }
}