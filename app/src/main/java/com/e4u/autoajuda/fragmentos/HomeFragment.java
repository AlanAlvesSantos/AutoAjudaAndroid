package com.e4u.autoajuda.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.activities.AmizadeActivity;
import com.e4u.autoajuda.activities.EstudosActivity;
import com.e4u.autoajuda.activities.ExerciciosActivity;
import com.e4u.autoajuda.activities.MotivacionalActivity;
import com.e4u.autoajuda.activities.PessoaMelhorActivity;
import com.e4u.autoajuda.activities.RelacionamentoActivity;
import com.e4u.autoajuda.activities.SaudeActivity;
import com.e4u.autoajuda.activities.TrabalhoActivity;
import com.e4u.autoajuda.activities.VideoPlayerActivity;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LinearLayout llRelacionamento;
    LinearLayout llMotivacao;
    LinearLayout llAmizade;
    LinearLayout llEstudos;
    LinearLayout llTrabalho;
    LinearLayout llPessoaMelhor;
    LinearLayout llExercicios;
    LinearLayout llSaude;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View view){

        llRelacionamento = view.findViewById(R.id.llRelacionamento);
        llRelacionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), RelacionamentoActivity.class);
                startActivity(intent);
            }
        });

        llMotivacao = view.findViewById(R.id.llMotivacao);
                llMotivacao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(), MotivacionalActivity.class);
                        startActivity(intent);
            }
        });

        llAmizade = view.findViewById(R.id.llAmizade);
        llAmizade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AmizadeActivity.class);
                startActivity(intent);
            }
        });

        llTrabalho = view.findViewById(R.id.llTrabalho);
        llTrabalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), TrabalhoActivity.class);
                startActivity(intent);
            }
        });

        llEstudos = view.findViewById(R.id.llEstudos);
        llEstudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), EstudosActivity.class);
                startActivity(intent);
            }
        });

        llPessoaMelhor = view.findViewById(R.id.llPessoaMelhor);
        llPessoaMelhor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), PessoaMelhorActivity.class);
                startActivity(intent);
            }
        });

        llExercicios = view.findViewById(R.id.llExercicios);
        llExercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ExerciciosActivity.class);
                startActivity(intent);
            }
        });

        llSaude = view.findViewById(R.id.llSaude);
        llSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SaudeActivity.class);
                startActivity(intent);
            }
        });



    }
}