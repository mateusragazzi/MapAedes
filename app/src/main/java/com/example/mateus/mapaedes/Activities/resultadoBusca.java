package com.example.mateus.mapaedes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mateus.mapaedes.Adapters.AddAdapter;
import com.example.mateus.mapaedes.Fragments.Buscar;
import com.example.mateus.mapaedes.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by zazah on 08/08/2016.
 */
public class resultadoBusca extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private ArrayList<String> Anome = new ArrayList<>();
    private ArrayList<String> Aendereco = new ArrayList<>();
    private ArrayList<Integer> Adoenca = new ArrayList<>();
    public ListView lv;
    public int a = 0;
    private String pendereco, pnome, pdoenca;


    private Button grafico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.resultado_busca);
        setContentView(R.layout.activity_reultado_busca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        grafico = (Button) findViewById(R.id.grafico);

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.fab2);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VaiParaMapa();
            }
        });

        lv = (ListView) findViewById(R.id.buscaas);
        lv.setAdapter(new AddAdapter(this, Adoenca, Anome, Aendereco));

        if (Buscar.endereco == 0) {
            FAB.hide();
        }

        if (Buscar.tipo == 0) {
            grafico.setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < Buscar.result.size(); i++) {
            pdoenca = Buscar.result.get(i).getTipo();
            pnome = Buscar.result.get(i).getNome();
            pendereco = Buscar.result.get(i).getEndereco();


            switch (pdoenca) {
                case "Dengue":
                    Adoenca.add(R.mipmap.red);
                    break;
                case "Zika vírus":
                    Adoenca.add(R.mipmap.green);
                    break;
                case "Chikungunya":
                    Adoenca.add(R.mipmap.blue);
                    break;
                case "Guillain barré":
                    Adoenca.add(R.mipmap.yellow);
                    break;
                case "Nyongnyong":
                    Adoenca.add(R.mipmap.orange);
                    break;
                default:
                    Adoenca.add(R.mipmap.point);


            }
            Anome.add(pnome + " - " + pdoenca);
            Aendereco.add(pendereco);

        }


    }

    private void VaiParaMapa() {
        Intent intent = new Intent(this, resultadoComMapa.class);
        startActivity(intent);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), Main.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void GRAFICO(View view) {
        Intent myIntent = new Intent(getApplicationContext(), ResultadoComGrafico.class);
        myIntent.putStringArrayListExtra("Doencas", Anome);
        startActivity(myIntent);
    }

}




