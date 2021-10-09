package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.e4u.autoajuda.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RotinaMatinalActivity extends AppCompatActivity {

    List<String> listaRotinas = new ArrayList<>();
    TextView txtRotina1, txtRotina2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotina_matinal);
        txtRotina1 = findViewById(R.id.txtRotina1);
        txtRotina2 = findViewById(R.id.txtRotina2);

        popularListaRotina();

    }

    private void popularListaRotina(){

        Calendar calendar = Calendar.getInstance();
        int selected = calendar.get(Calendar.DAY_OF_WEEK);

        if (selected == 0){
            listaRotinas.add(getString(R.string.r1));
            listaRotinas.add(getString(R.string.r2));
        }
        else if (selected == 1){
            listaRotinas.add(getString(R.string.r3));
            listaRotinas.add(getString(R.string.r4));
        }
        else if (selected == 2){
            listaRotinas.add(getString(R.string.r5));
            listaRotinas.add(getString(R.string.r6));
        }
        else if (selected == 3){
            listaRotinas.add(getString(R.string.r7));
            listaRotinas.add(getString(R.string.r8));
        }
        else if (selected == 4){
            listaRotinas.add(getString(R.string.r9));
            listaRotinas.add(getString(R.string.r10));
        }
        else if (selected == 5){
            listaRotinas.add(getString(R.string.r11));
            listaRotinas.add(getString(R.string.r12));
        }
        else if (selected == 6){
            listaRotinas.add(getString(R.string.r13));
            listaRotinas.add(getString(R.string.r14));
        }
        txtRotina1.setText(listaRotinas.get(0));
        txtRotina2.setText(listaRotinas.get(1));
    }
}