package com.example.mateus.mapaedes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mateus.mapaedes.Activities.Main;
import com.example.mateus.mapaedes.Adapters.PlaceAutocompleteAdapter;
import com.example.mateus.mapaedes.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


/**
 * Created by zazah on 13/02/2017.
 */

public class AdicionarCaso extends Fragment {
    public ArrayAdapter<String> opcoes;
    public static Spinner spinner;
    private PlaceAutocompleteAdapter mAdapter;
    public static AutoCompleteTextView Endereço;
    public static String PEndereço;
    public static EditText nomeE;
    View.OnClickListener mAutocompleteClickListener;
    public static final LatLngBounds BOUNS_CAMPO_GRANDE = new LatLngBounds(new LatLng(-20.595001, -54.503947), new LatLng(-20.364035, -54.765508));


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adicionar_caso_fragment, container, false);

        //Opções Doença - Spinner
        spinner = (Spinner) v.findViewById(R.id.spinner);
        opcoes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        opcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(opcoes);

        opcoes.add("Dengue");
        opcoes.add("Zika");
        opcoes.add("Chicungunya");
        opcoes.add("Nyongnyong");
        opcoes.add("Guillain barré");

        Endereço = (AutoCompleteTextView) v.findViewById(R.id.endereço);
        nomeE = (EditText) v.findViewById(R.id.NomeP);


        Main act = (Main) getActivity();
        Endereço.setOnClickListener(mAutocompleteClickListener);

        mAdapter = new PlaceAutocompleteAdapter(act, android.R.layout.simple_list_item_1,
                act.getmGoogleApiClient(), BOUNS_CAMPO_GRANDE, null);
        Endereço.setAdapter(mAdapter);
        PEndereço = Endereço.getText().toString();


        return v;
    }

    public AdicionarCaso() {

    }

    public interface OnFragmentInteractionListener {
    }
}
