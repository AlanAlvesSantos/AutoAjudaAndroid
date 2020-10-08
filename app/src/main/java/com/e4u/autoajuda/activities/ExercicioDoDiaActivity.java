package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.Utilities;
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


public class ExercicioDoDiaActivity extends AppCompatActivity {

    ImageView imgexerciciododia;
    TextView txtTitulo;
    TextView txtDescricaoDoExercicio;
    Activity activity;
    String parsedString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_do_dia);

        activity = this;

        imgexerciciododia = findViewById(R.id.imgexerciciododia);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricaoDoExercicio = findViewById(R.id.txtDescricaoDoExercicio);
        getData();
    }

    private void getData() {

        if (hasConnection()) {

            new Thread(new Runnable() {
                public void run() {
                    try {

                        String urlLoad = "http://webinfoco.com.br/filesla/AA/exercicioDia.json";

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

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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
            JSONArray array = json.getJSONArray("ExercicioDia");
            NewsModelo exercicio = null;
            boolean add = false;

            for (int i = 0; i < array.length(); i++) {
                exercicio = new NewsModelo();
                JSONObject object = array.getJSONObject(i);

                exercicio.setId(object.getInt("id"));
                exercicio.setImage(object.getString("image"));
                exercicio.setTitle(object.getString("title"));
                exercicio.setDescription(object.getString("description"));
            }


            txtDescricaoDoExercicio.setText(exercicio.getDescription());
            txtTitulo.setText(exercicio.getTitle());

            Glide.with(activity)
                    .load(exercicio.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imgexerciciododia);



        } catch (JSONException ex) {

        }
    }
}