package com.example.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapplication.Activities.fotometro;
import com.example.myapplication.Activities.fuentePoder;
import com.example.myapplication.Activities.fuenteVoltaje;
import com.example.myapplication.Activities.generadorSenales;
import com.example.myapplication.Activities.multimetro;
import com.example.myapplication.Activities.osciloscopio;
import com.example.myapplication.Activities.puntas;
import com.example.myapplication.Models.Material;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private ArrayList <Material> listaMaterial;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public MaterialAdapter(ArrayList<Material> listaMaterial, int layout, OnItemClickListener itemClickListener) {
        this.listaMaterial = listaMaterial;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MaterialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        context = parent.getContext();
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MaterialViewHolder holder, int position) {

        holder.bind(listaMaterial.get(position),itemClickListener);

        switch (position){

            case 0:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, multimetro.class);
                        context.startActivity(i);
                        Animatoo.animateShrink(context);

                    }
                });
                break;

            case 1:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, osciloscopio.class);
                        context.startActivity(i);
                        Animatoo.animateCard(context);
                    }
                });
                break;

            case 2:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, generadorSenales.class);
                        context.startActivity(i);
                        Animatoo.animateZoom(context);
                    }
                });
                break;

            case 3:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, puntas.class);
                        context.startActivity(i);
                        Animatoo.animateFade(context);
                    }
                });
                break;

            case 4:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, fotometro.class);
                        context.startActivity(i);
                        Animatoo.animateDiagonal(context);
                    }
                });
                break;

            case 5:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, fuentePoder.class);
                        context.startActivity(i);
                        Animatoo.animateSplit(context);
                    }
                });
                break;

            case 6:
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, fuenteVoltaje.class);
                        context.startActivity(i);
                        Animatoo.animateSpin(context);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return listaMaterial.size();
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private ImageView imageViewPoster;
        private RelativeLayout parentLayout;


        public MaterialViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewTitle);
            imageViewPoster =  itemView.findViewById(R.id.imageViewPoster);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

        public void bind(final Material material, final OnItemClickListener listener) {
            textViewName.setText(material.getName());
            //Picasso.with(context).load(material.getMaterial()).fit().into(imageViewPoster);
            imageViewPoster.setImageResource(material.getMaterial());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(material, getAdapterPosition());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Material material, int position);
    }
}
