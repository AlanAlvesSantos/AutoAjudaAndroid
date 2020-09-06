package com.e4u.autoajuda.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.modelos.VideoModelo;


public class VideoFragment extends Fragment {

    VideoModelo videoModelo = new VideoModelo();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        criarListaDeVideos();

        WebView displayYoutubeVideo = (WebView) getView().findViewById(R.id.mWebView);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(videoModelo.getVideoURL(), "text/html", "utf-8");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    private void criarListaDeVideos(){
        videoModelo.setVideoID(1);
        videoModelo.setVideoImagem("");
        videoModelo.setVideoTitle("MOTIVAÇÃO FORTE PARA TODOS OS DIAS DA SEMANA - MOTIVACIONAL NANDO PINHEIRO");
        videoModelo.setVideoURL("https://www.youtube.com/embed/ETzMjtUpKYs");

    }
}