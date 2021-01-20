package com.example.myapplication.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapters.MaterialAdapter;
import com.example.myapplication.Models.Material;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MaterialFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MaterialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MaterialFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerMaterial;
    private RecyclerView.Adapter mAdapter;

    ArrayList<Material> listaMaterial;

    public MaterialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    public static MaterialFragment newInstance(String param1, String param2) {
        MaterialFragment fragment = new MaterialFragment();
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
        View vista = inflater.inflate(R.layout.fragment_material, container, false);

        listaMaterial = new ArrayList<>();
        recyclerMaterial = vista.findViewById(R.id.recyclerView);
        recyclerMaterial.setLayoutManager(new LinearLayoutManager(getContext()));

        listaMaterial= this.getAllMaterial();

        mAdapter = new MaterialAdapter(listaMaterial, R.layout.recycler_view_item, new MaterialAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Material material, int position) {

            }
        });

        // Lo usamos en caso de que sepamos que el layout no va a cambiar de tamaño, mejorando la performance
        recyclerMaterial.setHasFixedSize(true);
        // Añade un efecto por defecto, si le pasamos null lo desactivamos por completo
        recyclerMaterial.setItemAnimator(new DefaultItemAnimator());

        recyclerMaterial.setAdapter(mAdapter);


        return vista;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private ArrayList<Material> getAllMaterial() {
        return new ArrayList<Material>() {{
            add(new Material("Multimetro", R.drawable.multimetro));
            add(new Material("Osciloscopio", R.drawable.osciloscopio));
            add(new Material("Generador de señales", R.drawable.generador));
            add(new Material("Puntas de generador", R.drawable.puntas));
            add(new Material("Fotometro",R.drawable.fotometro));
            add(new Material("Fuente de poder", R.drawable.fuente));
            add(new Material("Fuente de Voltaje",R.drawable.fuentevoltaje));
        }};
    }
}

