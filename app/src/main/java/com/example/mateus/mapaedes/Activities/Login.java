package com.example.mateus.mapaedes.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.mapaedes.Adapters.BancoDeDados;
import com.example.mateus.mapaedes.Adapters.BancoDeDadosAdapter;
import com.example.mateus.mapaedes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.id;

/**
 * Created by zazah on 13/02/2017.
 */

public class Login extends AppCompatActivity {
    @BindView(R.id.login_user)
    EditText mLoginUser;
    @BindView(R.id.login_pswd)
    EditText mLoginPswd;

     BancoDeDados mBancoDeDados = new BancoDeDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);


        SQLiteDatabase banco = mBancoDeDados.getReadableDatabase();


        //banco.execSQL("DELETE FROM login"); //delete all rows in a table


        Cursor cursor = banco.query("login", null, null, null, null, null, null);

        if (cursor.moveToFirst()){

           String  usuario = cursor.getString(cursor.getColumnIndex("usuario"));
           String  senha = cursor.getString(cursor.getColumnIndex("senha"));



            if (usuario != null ) {
                Intent intent = new Intent(this, Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }




    }

    @OnClick(R.id.login_button)
    public void Login() {

        String usuario = mLoginUser.getText().toString();
        String senha = mLoginPswd.getText().toString();
        String cidade = "Campo Grande";
        int idUsuario = 1;
        double lat = -22.9066800;
        double lng = -43.5529000;

        BancoDeDadosAdapter c = new BancoDeDadosAdapter();
        c.setId_usuario(idUsuario);
        c.setUsuario(usuario);
        c.setSenha(senha);
        c.setCidade(cidade);
        c.setLat(lat);
        c.setLng(lng);
        mBancoDeDados.insertContact(c);
        Toast.makeText(this,"Login realizado com sucesso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, Main.class);
        startActivity(intent);
    }

    @OnClick(R.id.cadastro_button)
    public void Cadastro() {
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }
}
