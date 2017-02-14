package com.example.mateus.mapaedes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mateus.mapaedes.R;

import static com.example.mateus.mapaedes.R.id.content_main;
/**
 * Created by zazah on 13/02/2017.
 */

public class Login extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void Login (View v){
        Intent intent = new Intent(Login.this, Main.class);
        startActivity(intent);
    }

}
