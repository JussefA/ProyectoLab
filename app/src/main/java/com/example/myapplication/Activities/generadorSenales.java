package com.example.myapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapplication.R;
import com.example.myapplication.Adapters.materialCustomAdapter;
import com.example.myapplication.entidades.MaterialEnt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class generadorSenales extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    String pedido, evaluar;
    ImageView image;
    static ArrayList  <String> pedidoGeneradorSenales = new ArrayList<>();
    private ListView listViewMat;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    MaterialEnt materialEnt = new MaterialEnt();
    final ArrayList<String> names = new ArrayList<>();
    final ArrayList<String> etiqueta = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generador_senales);

        listViewMat =  findViewById(R.id.listGen);
        request = Volley.newRequestQueue(generadorSenales.this);

        cargarWebServices();


        listViewMat.setClickable(true);

        listViewMat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        pedido = names.get(0);
                        evaluar = etiqueta.get(0);
                        if(evaluar.equals("No disponible")){
                            Toast.makeText(generadorSenales.this,"Este material no se puede seleccionar", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(generadorSenales.this,"Seleccionaste el " + pedido, Toast.LENGTH_SHORT).show();
                            pedidoGeneradorSenales.add(pedido);
                        }
                        break;

                    case 1:
                        pedido = names.get(1);
                        evaluar = etiqueta.get(1);
                        if(evaluar.equals("No disponible")){
                            Toast.makeText(generadorSenales.this,"Este material no se puede seleccionar", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(generadorSenales.this,"Seleccionaste el " + pedido, Toast.LENGTH_SHORT).show();
                            pedidoGeneradorSenales.add(pedido);
                        }
                        break;
                    case 2:
                        pedido = names.get(2);
                        evaluar = etiqueta.get(2);
                        if(evaluar.equals("No disponible")){
                            Toast.makeText(generadorSenales.this,"Este material no se puede seleccionar", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(generadorSenales.this,"Seleccionaste el " + pedido, Toast.LENGTH_SHORT).show();
                            pedidoGeneradorSenales.add(pedido);
                        }
                        break;
                    case 3:
                        pedido = names.get(3);
                        evaluar = etiqueta.get(3);
                        if(evaluar.equals("No disponible")){
                            Toast.makeText(generadorSenales.this,"Este material no se puede seleccionar", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(generadorSenales.this,"Seleccionaste el " + pedido, Toast.LENGTH_SHORT).show();
                            pedidoGeneradorSenales.add(pedido);
                        }
                        break;
                }

            }
        });

        image = findViewById(R.id.backimageView);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(generadorSenales.this,MainActivity.class);
                startActivity(i);
                Animatoo.animateSlideRight(generadorSenales.this);
                finish();
            }
        });

    }

    public ArrayList<String> getGeneradorSenales (){
        return pedidoGeneradorSenales;

    }

    private void cargarWebServices() {
        progress = new ProgressDialog(generadorSenales.this);
        progress.setMessage("Consultando...");

        String url = "http://189.223.106.193/proyectolab/consultarListaGen.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(generadorSenales.this,"No se pudo Consultar "+error.toString(),Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.i("ERROR",error.toString());
        progress.hide();

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("usuario");

        try {

            for (int x = 0; x<json.length();x++){

                JSONObject jsonObject;
                jsonObject = json.getJSONObject(x);
                materialEnt.setEtiqueta(jsonObject.optString("etiqueta"));
                materialEnt.setEstado(jsonObject.optString("estado"));

                names.add(materialEnt.getEtiqueta());
                etiqueta.add(materialEnt.getEstado());

                materialCustomAdapter materialCustomAdapter = new materialCustomAdapter(this, R.layout.item_mult, names, etiqueta);
                listViewMat.setAdapter(materialCustomAdapter);

            }
            progress.hide();
        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(generadorSenales.this,"Mensaje.." + response,Toast.LENGTH_SHORT).show();
            progress.hide();

        }

    }
}