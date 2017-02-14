package com.example.mateus.mapaedes.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mateus.mapaedes.R;

/**
 * Created by zazah on 13/02/2017.
 */

public class AdicionarCaso extends Fragment {
    public  ArrayAdapter<String> opcoes;
    public  Spinner spinner;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.adicionar_caso_fragment, container, false);

        //Opções Doença - Spinner
        spinner = (Spinner) g.findViewById(R.id.spinner);
        opcoes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        opcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(opcoes);

        opcoes.add("Dengue");
        opcoes.add("Zika vírus");
        opcoes.add("Chikungunya");
        opcoes.add("Nyongnyong");
        opcoes.add("Guillaint barré");

        return g;
    }

}
