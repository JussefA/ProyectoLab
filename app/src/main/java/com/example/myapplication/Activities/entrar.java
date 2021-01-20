package com.example.myapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Fragments.EstudianteFragment;
import com.example.myapplication.R;
import com.example.myapplication.entidades.Usuario;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class entrar extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    Button entrar;
    EditText numero, contrasena;
    private KenBurnsView kbv;
    private boolean moving = true;
    public static String numeroControl, sena, numeroCompara,
            nombre, apellidos, apellidoM, carrera;
    RequestQueue request;
    ProgressDialog progreso;
    JsonObjectRequest jsonObjectRequest;
    Usuario miUsuario = new Usuario();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrar);

        kbv = findViewById(R.id.kvb);
        numero = findViewById(R.id.txtControl);
        contrasena = findViewById(R.id.txtContrasena);
        request = Volley.newRequestQueue(entrar.this);


        AccelerateDecelerateInterpolator adi = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(2000, adi);
        kbv.setTransitionGenerator(generator);
        kbv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moving) {
                    kbv.pause();
                    moving = false;
                } else {
                    kbv.resume();
                    moving = true;
                }
            }
        });

        entrar = findViewById(R.id.entrarbtn);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroControl = numero.getText().toString();
                sena = contrasena.getText().toString();
                
                cargarWebServices();


                nombre = miUsuario.getNombre_usuario();
                apellidos = miUsuario.getApellido_usuario();
                //apellidoM = miUsuario.getApeliido_Materno();
                carrera = miUsuario.getCarrera_usuario();
                numeroCompara = miUsuario.getId_usuario();

                //Intent intent = new Intent(entrar.this, MainActivity.class);
                //startActivity(intent);


                Bundle miBundle = new Bundle();

                if(numeroControl.equals(numeroCompara) && sena.equals("tec123")){

                    Toast.makeText(entrar.this, "Bienvenido(a) " + nombre,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(entrar.this, MainActivity.class);
                    startActivity(intent);

                } else if(numeroControl.equals(numeroCompara) && sena != "tec123"){
                    Toast.makeText(entrar.this, "La contraseña es incorrecta",Toast.LENGTH_SHORT).show();
                } else if(numeroControl != numeroCompara && sena.equals("tec123")){
                    Toast.makeText(entrar.this, "Numero de control no encontrado",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void cargarWebServices() {
        progreso = new ProgressDialog(entrar.this);
        progreso.setMessage("Consultando.....");


        String url = "http://189.223.106.193/proyectolab/acceder.php?id_usuario=" + numero.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(entrar.this,"No se pudo Consultar "+error.toString(),Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.i("ERROR",error.toString());
        progreso.hide();


    }


    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        //Toast.makeText(entrar.this,"Mensaje.." + response,Toast.LENGTH_LONG).show();


        JSONArray json = response.optJSONArray("usuario");
        JSONObject jsonObject;

        try {
            jsonObject = json.getJSONObject(0);
            miUsuario.setId_usuario(jsonObject.optString("id_usuario"));
            miUsuario.setNombre_usuario(jsonObject.optString("nombre_usuario"));
            miUsuario.setApellido_usuario(jsonObject.optString("apellido_usuario"));
            //miUsuario.setApeliido_Materno(jsonObject.optString("apellido_materno"));
            miUsuario.setCarrera_usuario(jsonObject.optString("carrera_usuario"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String pasarNumero(){
        return numeroCompara;
    }

    public String pasarNombre(){return nombre;}
    public String pasarApellidos(){return apellidos;}
    //public String pasarApellidoM(){return  apellidoM;}
    public String pasarCarrera(){return carrera;}


}


