package com.example.mateus.mapaedes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.mateus.mapaedes.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void Login() {
        Intent intent = new Intent(Login.this, Main.class);
        startActivity(intent);
    }

}
