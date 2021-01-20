package com.example.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Activities.entrar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EstudianteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EstudianteFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ListView listViewEst;
    List<String> nombresPedido = new ArrayList<>();
    PedidoFragment fragment = new PedidoFragment();
    private TextView nombre, numero, carrera, tiempo, hora;
    public String nombreS, numeroS, carreraS, tiempoS;
    entrar entrar = new entrar();

    private OnFragmentInteractionListener mListener;

    public EstudianteFragment() {
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
    public static EstudianteFragment newInstance(String param1, String param2) {
        EstudianteFragment fragment = new EstudianteFragment();
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
        View vista = inflater.inflate(R.layout.fragment_estudiante_fragment, container, false);

        listViewEst = vista.findViewById(R.id.listPedidoEst);

        nombre = vista.findViewById(R.id.txtnombre);
        numero = vista.findViewById(R.id.txtControl);
        carrera = vista.findViewById(R.id.txtCarrera);
        tiempo = vista.findViewById(R.id.txtTiempo);
        hora = vista.findViewById(R.id.txtFechaHora);

        nombreS = "Nombre: " +  entrar.pasarNombre() + " " + entrar.pasarApellidos();
        carreraS = "Carrera: " + entrar.pasarCarrera();
        numeroS = "Numero de control: " + entrar.pasarNumero();

        nombre.setText(nombreS);
        numero.setText(numeroS);
        carrera.setText(carreraS);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy-hh:mm aa");
        String horas = simpleDateFormat.format(new Date());

        hora.setText(horas);





        return vista;
    }

    public void reverseTimer(int Seconds, final TextView tv) {

        new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);

                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);
                String tiempo = "TIEMPO : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds);

                tv.setText(tiempo);
            }

            public void onFinish() {
                tv.setText("Completed");
            }
        }.start();
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

    @Override
    public void onResume() {
        super.onResume();

        tiempoS = fragment.getTiempo();
        int minutos = 0;
        int segundos = 0;
        if(tiempoS != null){
            minutos = Integer.parseInt(tiempoS);
            segundos = minutos * 60;
        } else{
            minutos = 0;
            segundos = 0;
        }


        reverseTimer(segundos,tiempo);
        nombresPedido.clear();

        nombresPedido.addAll(fragment.getPedidoTotal());

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                nombresPedido
        );

        listViewEst.setAdapter(listViewAdapter);



    }
}

