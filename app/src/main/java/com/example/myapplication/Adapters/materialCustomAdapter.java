package com.example.myapplication.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

//Adapter solo con un texto
public class materialCustomAdapter extends BaseAdapter {

    private Context mContext;
    private int layout;
    private ArrayList<String> materialList;
    private ArrayList<String> etiquetas;



    public materialCustomAdapter(Context mContext, int layout, ArrayList<String> materialList, ArrayList<String> etiquetas) {
        this.mContext = mContext;
        this.layout = layout;
        this.materialList = materialList;
        this.etiquetas = etiquetas;
    }

    @Override
    public int getCount() {
        return this.materialList.size();
    }
    public int getCount2() {
        return this.etiquetas.size();
    }


    @Override
    public Object getItem(int position) {
        return this.materialList.get(position);
    }

    public Object getItem2(int position) {
        return this.etiquetas.get(position);
    }

    @Override
    public long getItemId(int id2) {
        return id2;
    }

    public long getItemId2(int id2) {
        return id2;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
        v = layoutInflater.inflate(R.layout.item_mult, null);

        String currentName = materialList.get(position);

        String currentEtiqueta = etiquetas.get(position);

        TextView textView1 = v.findViewById(R.id.txtmultimetro);
        textView1.setText(currentName);

        TextView textView2 = v.findViewById(R.id.txtEtiqueta);
        textView2.setText(currentEtiqueta);

        return v;


    }

}