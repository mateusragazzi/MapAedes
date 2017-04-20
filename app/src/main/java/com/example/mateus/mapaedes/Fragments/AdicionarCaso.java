package com.example.mateus.mapaedes.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mateus.mapaedes.Adapters.BancoDeDados;
import com.example.mateus.mapaedes.Adapters.PlaceAutocompleteAdapter;
import com.example.mateus.mapaedes.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


/**
 * Created by zazah on 13/02/2017.
 */

public class AdicionarCaso extends Fragment {
    public  ArrayAdapter<String> opcoes;
    public  static Spinner spinner;
    private PlaceAutocompleteAdapter mAdapter;
    public static AutoCompleteTextView Endereço;
    public static String PEndereço;
    public SearchView sv;
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
        opcoes.add("Zika vírus");
        opcoes.add("Chikungunya");
        opcoes.add("Nyongnyong");
        opcoes.add("Guillaint barré");

        Endereço = (AutoCompleteTextView) v.findViewById(R.id.endereço);
        nomeE =(EditText)v.findViewById(R.id.NomeP);

        Endereço.setOnClickListener(mAutocompleteClickListener);


        BancoDeDados helper = new BancoDeDados(getActivity());
        SQLiteDatabase bancoo = helper.getReadableDatabase();
        Cursor cursor = bancoo.query("casos", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.e("Condiçao", "entrou");
                String nomeP = cursor.getString(cursor.getColumnIndex("Pnome"));
                String doencaP = cursor.getString(cursor.getColumnIndex("Pdoenca"));
                String enderecoP = cursor.getString(cursor.getColumnIndex("Pendereco"));
                Double latP = cursor.getDouble(cursor.getColumnIndex("Plat"));
                Double lngP = cursor.getDouble(cursor.getColumnIndex("Plng"));

                Toast.makeText(getActivity(), nomeP, Toast.LENGTH_SHORT).show();


            }while (cursor.moveToNext());
        }

        return v;
    }

    public AdicionarCaso() {

    }

    public interface OnFragmentInteractionListener {
    }
}
