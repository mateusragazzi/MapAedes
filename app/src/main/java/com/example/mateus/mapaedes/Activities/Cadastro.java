package com.example.mateus.mapaedes.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mateus.mapaedes.Adapters.BancoDeDados;
import com.example.mateus.mapaedes.R;
import com.example.mateus.mapaedes.helpers.User;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.makeText;

/**
 * Created by zazah on 14/02/2017.
 */

public class Cadastro extends AppCompatActivity {
    @BindView(R.id.editText3)
    EditText nome;
    @BindView(R.id.editText5)
    EditText usuario;
    @BindView(R.id.editText6)
    EditText senha;
    @BindView(R.id.add)
    EditText cidade;
    @BindView(R.id.spinner2)
    Spinner pessoas;
    @BindView(R.id.cadastro_toolbar)
    Toolbar mToolbar;

    String Nome;
    String Email;
    String Senha;
    int Pessoas;
    String Cidade;

    BancoDeDados helper = new BancoDeDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setupToolbar();

    }

    public void setupToolbar() {
        mToolbar.setTitle(R.string.cadastrar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void CADASTRO(View view) throws IOException {

        Nome = nome.getText().toString();
        Email = usuario.getText().toString();
        Cidade = cidade.getText().toString();
        Senha = senha.getText().toString();
        Pessoas = pessoas.getSelectedItemPosition();


        //Adapter -- Pegando lat e lng da Cidade
        Geocoder gcc = new Geocoder(this);
        List<Address> list = gcc.getFromLocationName(Cidade, 1);
        Address add = list.get(0);
        String locality = add.getLocality();
        makeText(this, locality, Toast.LENGTH_LONG).show();

        final double lat = add.getLatitude();

        final double lng = add.getLongitude();

        User user = new User();
        user.setName(Nome);
        user.setEmail(Email);
        user.setPassword(Senha);
        user.setCity(Cidade);
        user.setLat(lat);
        user.setLng(lng);
        user.setType(Pessoas);

        user.save();


        //Colocando no BD
      /*  BancoDeDadosAdapter c = new BancoDeDadosAdapter();
        c.setUserUser(Usuario);
        c.setCidadeUser(Cidade);
        c.setNomeUser(Nome);
        c.setSenhaUser(Senha);
        c.setTipoUser(Pessoas);
        c.setLatUser(lat);
        c.setLngUser(lng);
        helper.insertContactt(c);*/

        makeText(this, "Registered with success!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Cadastro.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}



