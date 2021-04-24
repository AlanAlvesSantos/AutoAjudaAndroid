package com.e4u.autoajuda.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.activities.SalvarDiarioActivity;
import com.e4u.autoajuda.modelos.DiarioModel;
import java.util.ArrayList;
import java.util.List;

public class DiarioAdapter extends RecyclerView.Adapter<DiarioAdapter.VHItems> {

    private List<DiarioModel> arrayList = new ArrayList<>();
    private Activity mContext;

    public DiarioAdapter(Activity mContext, List<DiarioModel> listaDiario) {
        this.mContext = mContext;
        this.arrayList = listaDiario;
    }

    @Override
    public DiarioAdapter.VHItems onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_diario, parent, false);
        return new DiarioAdapter.VHItems(view);
    }

    @Override
    public void onBindViewHolder(final DiarioAdapter.VHItems holder, final int position) {

        final DiarioModel model = arrayList.get(position);

        holder.txtDataDiario.setText(model.getData());

        if(model.getTextoDia().length() > 60){
            holder.txtDescricaoDiario.setText(model.getTextoDia().substring(0, 100) + " ...");
        }else{
            holder.txtDescricaoDiario.setText(model.getTextoDia());
        }

        holder.rating.setRating(model.getAvaliacao());

        holder.cardDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SalvarDiarioActivity.class);
                intent.putExtra("id", model.getDiarioID());
                intent.putExtra("rating", model.getAvaliacao());
                intent.putExtra("texto", model.getTextoDia());
                mContext.startActivityForResult(intent, 10200);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class VHItems extends RecyclerView.ViewHolder {
        TextView txtDataDiario, txtDescricaoDiario;
        CardView cardDiario;
        RatingBar rating;

        public VHItems(View itemView) {

            super(itemView);

            rating = itemView.findViewById(R.id.rating);
            cardDiario =  itemView.findViewById(R.id.cardDiario);
            txtDataDiario = itemView.findViewById(R.id.txtDataDiario);
            txtDescricaoDiario = itemView.findViewById(R.id.txtDescricaoDiario);
        }
    }
}