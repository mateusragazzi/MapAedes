package com.example.mateus.mapaedes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mateus.mapaedes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zazah on 14/02/2017.
 */

public class Cadastro extends AppCompatActivity {
    @BindView(R.id.editText3)
    EditText nome;
    @BindView(R.id.editText5)
    EditText usuario;
    @BindView(R.id.add)
    EditText senha;
    @BindView(R.id.spinner2)
    Spinner pessoas;

    String Nome, Usuario, Senha, Pessoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button3)
    public void Cadastro(){
        Nome= nome.getText().toString();
        Usuario=usuario.getText().toString();
        Senha = senha.getText().toString();
        Pessoas = String.valueOf(pessoas.getSelectedItem());

        Toast.makeText(this, Nome +Usuario+ Senha+ Pessoas, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
