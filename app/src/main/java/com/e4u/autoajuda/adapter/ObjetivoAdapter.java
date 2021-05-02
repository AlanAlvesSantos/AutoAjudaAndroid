package com.e4u.autoajuda.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.activities.ObjetivosSalvarActivity;
import com.e4u.autoajuda.modelos.ItemListaModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Felipe Carvalho on 29-04-2021
 */
public class ObjetivoAdapter extends RecyclerView.Adapter<ObjetivoAdapter.VHItems> {

    private List<ItemListaModel> arrayList = new ArrayList<>();
    private Activity mContext;

    public ObjetivoAdapter(Activity mContext, List<ItemListaModel> lista) {
        this.mContext = mContext;
        this.arrayList = lista;
    }

    @Override
    public ObjetivoAdapter.VHItems onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_objetivo, parent, false);
        return new ObjetivoAdapter.VHItems(view);
    }

    @Override
    public void onBindViewHolder(final ObjetivoAdapter.VHItems holder, final int position) {

        final ItemListaModel model = arrayList.get(position);

        holder.txtTituloObjetivo.setText(model.getItemDescription());


        if (!model.getItemImagem().equals(""))
            Glide.with(mContext).load(model.getItemImagem()).into(holder.imgMeta);


        holder.cardObjetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ObjetivosSalvarActivity.class);
                intent.putExtra("id", model.getItemID());
                mContext.startActivityForResult(intent, 10200);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class VHItems extends RecyclerView.ViewHolder {
        TextView txtTituloObjetivo;
        CardView cardObjetivo;
        CircleImageView imgMeta;

        public VHItems(View itemView) {

            super(itemView);

            cardObjetivo = itemView.findViewById(R.id.cardObjetivo);
            txtTituloObjetivo = itemView.findViewById(R.id.txtTituloObjetivo);
            imgMeta = itemView.findViewById(R.id.imgMeta);
        }
    }
}