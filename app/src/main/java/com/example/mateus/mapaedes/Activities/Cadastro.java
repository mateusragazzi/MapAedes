package com.example.mateus.mapaedes.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mateus.mapaedes.R;

import butterknife.ButterKnife;

/**
 * Created by zazah on 14/02/2017.
 */

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        ButterKnife.bind(this);
    }

}
