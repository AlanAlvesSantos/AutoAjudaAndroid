package com.e4u.autoajuda.fragmentos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.Utilities;
import com.e4u.autoajuda.adapter.NewsAdapter;
import com.e4u.autoajuda.modelos.NewsModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static com.e4u.autoajuda.Utilities.NEWS_URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticiasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticiasFragment extends Fragment {

    RecyclerView rvNoticias;
    Activity activity;
    List<NewsModelo> listaNoticias = new ArrayList<>();
    NewsAdapter adapter;
    String parsedString = "";
    public int indice = 1;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NoticiasFragment() {
    }
    public static NoticiasFragment newInstance(String param1, String param2) {
        NoticiasFragment fragment = new NoticiasFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);
        activity = getActivity();
        initComponents(view);
        return view;
    }

    private void initComponents(View v) {

        rvNoticias = v.findViewById(R.id.rvNoticias);
        getNewsFromServer();
    }

    private void getNewsFromServer() {
        loadNews();
    }

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    public void loadNews() {
        if (hasConnection()) {

            new Thread(new Runnable() {
                public void run() {
                    try {

                        String urlLoad = NEWS_URL;

                        URL url = new URL(urlLoad);
                        URLConnection conn = url.openConnection();
                        HttpURLConnection httpConn = (HttpURLConnection) conn;
                        httpConn.setAllowUserInteraction(false);
                        httpConn.setInstanceFollowRedirects(true);
                        httpConn.setRequestMethod("GET");
                        httpConn.connect();
                        InputStream is = httpConn.getInputStream();
                        parsedString = convertinputStreamToString(is);

                    } catch (Exception e) {
                        Log.d("MyTag", e.toString());
                        indice--;
                    }

                    activity.runOnUiThread(new Runnable() {
                        public void run() {

                            try {
                                carregarTela();
                            } catch (Exception ex) {
                                Utilities.buildErrorMessageDialog(activity, "Internet", ex.getMessage()).show();
                            }
                        }
                    });

                }
            }).start();

        } else {
            Utilities.buildErrorMessageDialog(activity, "Internet", "Dispositivo sem conexão com a Internet").show();
        }
    }

    public static String convertinputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            String sbFinal = "";

            sbFinal = sb.toString().replace("\n", "");

            return sbFinal;
        } else {
            return "";
        }
    }

    private void carregarTela() {
        try {
            JSONObject json = new JSONObject(parsedString);
            JSONArray array = json.getJSONArray("News");
            NewsModelo newsEntity = null;
            boolean add = false;

            for (int i = 0; i < array.length(); i++) {
                newsEntity = new NewsModelo();
                JSONObject object = array.getJSONObject(i);

                newsEntity.setId(object.getInt("id"));
                newsEntity.setImage(object.getString("image"));
                newsEntity.setTitle(object.getString("title"));
                newsEntity.setSubtitle(object.getString("subtitle"));
                newsEntity.setDescription(object.getString("description"));

                listaNoticias.add(newsEntity);
            }

            carregarLista();

        } catch (JSONException ex) {

        }
    }

    private void carregarLista() {

        adapter = new NewsAdapter(activity, listaNoticias);

        if (listaNoticias.size() > 0) {
            rvNoticias.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
            rvNoticias.setLayoutManager(mLayoutManager);
            rvNoticias.setItemAnimator(new DefaultItemAnimator());
            rvNoticias.setAdapter(adapter);
        } else {
            rvNoticias.setVisibility(View.INVISIBLE);
        }
    }
}