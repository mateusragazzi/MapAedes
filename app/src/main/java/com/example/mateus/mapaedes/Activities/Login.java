package com.example.mateus.mapaedes.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.mapaedes.Adapters.BancoDeDados;
import com.example.mateus.mapaedes.R;
import com.example.mateus.mapaedes.helpers.LoggedUser;
import com.example.mateus.mapaedes.helpers.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zazah on 13/02/2017.
 */

public class Login extends AppCompatActivity {
    @BindView(R.id.login_user)
    EditText mLoginUser;
    @BindView(R.id.login_pswd)
    EditText mLoginPswd;

    BancoDeDados bd = new BancoDeDados(this);

    int valorIncorreto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
            ButterKnife.bind(this);


       /* SQLiteDatabase banco = bd.getReadableDatabase();

        //  banco.execSQL("DELETE FROM login"); //delete all rows in a table


        Cursor cur = banco.rawQuery("SELECT EXISTS (SELECT 1 FROM login)", null);

        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) != 0) {
                Intent intent = new Intent(Login.this, Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }*/

        List<LoggedUser> userTotal = LoggedUser.listAll(LoggedUser.class);

        if (userTotal.size() != 0) {
            Intent intent = new Intent(Login.this, Main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

   /* public void LOGIN(View view) {

        int conta = 0;
        String usuario = mLoginUser.getText().toString();
        String senha = mLoginPswd.getText().toString();

        SQLiteDatabase banco = bd.getReadableDatabase();
        Cursor cursor = banco.query("usuario", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String userBd = cursor.getString(cursor.getColumnIndex("userUser"));
                String senhaBd = cursor.getString(cursor.getColumnIndex("senhaUser"));

                //Toast.makeText(this, userBd + " " + senhaBd, Toast.LENGTH_SHORT).show();

                if (usuario.equals(userBd) && senha.equals(senhaBd)) {
                    int ID = cursor.getInt(cursor.getColumnIndex("idUser"));
                    String CIDADE = cursor.getString(cursor.getColumnIndex("cidadeUser"));
                    int TIPO = cursor.getInt(cursor.getColumnIndex("tipoUser"));
                    Double PLAT = cursor.getDouble(cursor.getColumnIndex("latUser"));
                    Double PLNG = cursor.getDouble(cursor.getColumnIndex("lngUser"));


                    BancoDeDadosAdapter c = new BancoDeDadosAdapter();
                    c.setId_usuario(ID);
                    c.setUsuario(userBd);
                    c.setSenha(senhaBd);
                    c.setCidade(CIDADE);
                    c.setLat(PLAT);
                    c.setLng(PLNG);
                    c.setTipo(TIPO);
                    bd.insertContact(c);
                    Toast.makeText(this, "LoginBD realizado com sucesso", Toast.LENGTH_SHORT).show();
                    conta = 1;
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);

                }
            } while (cursor.moveToNext());
        }

        if (conta == 0) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Invalid email or password");
            dlg.setNeutralButton("OK", null);
            AlertDialog dialog = dlg.create();
            dialog.show();

        }
    }*/

    @OnClick(R.id.cadastro_button)
    public void Cadastro() {
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }

    public void LOGIN2(View view) {
        String usuario = mLoginUser.getText().toString();
        String senha = mLoginPswd.getText().toString();

        List<User> userName = User.find(User.class, "email = ?", usuario);

        // 0 ou 1 no i

        for (int i = 0; i < userName.size(); i++) {
            if (userName.get(i).getPassword().equals(senha)) {
                valorIncorreto = 1;

//                String Nome = userName.get(i).getName();
//                String Email = userName.get(i).getEmail();
//                String Senha = userName.get(i).getPassword();
//                String Cidade = userName.get(i).getCity();
//                Double lat = userName.get(i).getLat();
//                Double lng = userName.get(i).getLng();
//                int Pessoas = userName.get(i).getType();

                // TODO: 09/11/2017 FAZER O TESTE PARA LOGAR USER
                LoggedUser loggedUser = new LoggedUser(userName.get(i));
                loggedUser.save();

                Toast.makeText(this, "LoginBD realizado com sucesso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);
            }
        }

        if (valorIncorreto == 0) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Invalid email or password");
            dlg.setNeutralButton("OK", null);
            AlertDialog dialog = dlg.create();
            dialog.show();
        }
    }
}

