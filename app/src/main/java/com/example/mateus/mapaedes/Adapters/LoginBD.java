package com.example.mateus.mapaedes.Adapters;

import com.orm.SugarRecord;

/**
 * Created by zazah on 12/10/2017.
 */

public class LoginBD extends SugarRecord<LoginBD> {
    String usuario;
    String senha;
    String cidade;
    Double lat;
    Double lng;
    String tipo;

    public LoginBD() {

    }

    public LoginBD(String usuario, String senha, String cidade, Double lat, Double lng, String tipo) {
        this.usuario = usuario;
        this.senha = senha;
        this.cidade = cidade;
        this.lat = lat;
        this.lng = lng;
        this.tipo = tipo;
    }
}

