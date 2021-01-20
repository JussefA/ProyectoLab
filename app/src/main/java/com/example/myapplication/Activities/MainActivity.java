package com.example.myapplication.Activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myapplication.Fragments.EstudianteFragment;
import com.example.myapplication.Fragments.MaterialFragment;
import com.example.myapplication.Fragments.PedidoFragment;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements MaterialFragment.OnFragmentInteractionListener,
        EstudianteFragment.OnFragmentInteractionListener, PedidoFragment.OnFragmentInteractionListener{

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private EstudianteFragment estudianteFragment;
    private MaterialFragment materialFragment;
    private PedidoFragment pedidoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        estudianteFragment = new EstudianteFragment();
        materialFragment = new MaterialFragment();
        pedidoFragment = new PedidoFragment();

        setFragment(materialFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_material:
                        mMainNav.setItemBackgroundResource(R.color.Morado);

                        setFragment(materialFragment);
                        return true;

                    case R.id.nav_pedido:
                        mMainNav.setItemBackgroundResource(R.color.Azul);
                        setFragment(pedidoFragment);
                        return true;

                    case R.id.nav_estudiante:
                        mMainNav.setItemBackgroundResource(R.color.Verde);
                        setFragment(estudianteFragment);
                        return true;

                        default:
                            return false;
                }
            }


        });

        //Fin listener toolbar
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
