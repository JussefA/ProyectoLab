package com.example.myapplication.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.myapplication.Activities.fotometro;
import com.example.myapplication.Activities.fuentePoder;
import com.example.myapplication.Activities.fuenteVoltaje;
import com.example.myapplication.Activities.generadorSenales;
import com.example.myapplication.Activities.multimetro;
import com.example.myapplication.Activities.puntas;
import com.example.myapplication.Activities.osciloscopio;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PedidoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PedidoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidoFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button fin;
    private EditText tiempo;
    private SwipeMenuListView listViewPed;
    public multimetro multi = new multimetro();
    public osciloscopio osciloscopio = new osciloscopio();
    public fotometro fotom = new fotometro();
    public fuentePoder fPoder = new fuentePoder();
    public fuenteVoltaje fVolt = new fuenteVoltaje();
    public generadorSenales generador = new generadorSenales();
    public puntas puntas = new puntas();
    public static ArrayList<String> pedidoTotal = new ArrayList<>();
    public static ArrayList<String> pedidoEstudiante = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    public static String nombreS, numeroS, carreraS, tiempoS;


    private Context context;

    public PedidoFragment() {
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
    public static PedidoFragment newInstance(String param1, String param2) {
        PedidoFragment fragment = new PedidoFragment();
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
        View vista =  inflater.inflate(R.layout.fragment_pedido, container, false);
        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                pedidoTotal
        );

        fin = vista.findViewById(R.id.btnFinPedido);
        tiempo = vista.findViewById(R.id.edtMin);

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoS = tiempo.getText().toString();

                Toast.makeText(getContext(),"Pedido Finalizado",Toast.LENGTH_LONG).show();


                pedidoEstudiante.clear();
                pedidoEstudiante.addAll(pedidoTotal);
                pedidoTotal.clear();
                listViewAdapter.notifyDataSetChanged();

            }
        });

        listViewPed = vista.findViewById(R.id.listPedido);

        context = container.getContext();

        SwipeMenuCreator creator = new SwipeMenuCreator() {


            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(135,
                        186, 199)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
            
        };
        listViewPed.setMenuCreator(creator);


        pedidoTotal.clear();

        pedidoTotal.addAll(multi.getMultimetros());
        pedidoTotal.addAll(osciloscopio.getOsciloscopios());
        pedidoTotal.addAll(fotom.getFotometros());
        pedidoTotal.addAll(fPoder.getFuentePoder());
        pedidoTotal.addAll(fVolt.getFuenteVoltaje());
        pedidoTotal.addAll(generador.getGeneradorSenales());
        pedidoTotal.addAll(puntas.getPuntas());



        listViewPed.setAdapter(listViewAdapter);


        listViewPed.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        pedidoTotal.remove(position);
                        listViewAdapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return true;
            }
        });

        return vista;
    }

    public ArrayList<String> getPedidoTotal (){
        return pedidoEstudiante;
    }
    public String getTiempo(){return tiempoS;}

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
}

