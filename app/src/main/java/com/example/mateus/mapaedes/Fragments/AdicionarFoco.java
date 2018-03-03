package com.example.mateus.mapaedes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.mateus.mapaedes.Activities.Main;
import com.example.mateus.mapaedes.Adapters.PlaceAutocompleteAdapter;
import com.example.mateus.mapaedes.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by zazah on 27/02/2017.
 */

public class AdicionarFoco extends Fragment {

    public static AutoCompleteTextView endereço;
    private PlaceAutocompleteAdapter mAdapter;
    View.OnClickListener mAutocompleteClickListener;
    public static final LatLngBounds BOUNS_CAMPO_GRANDE = new LatLngBounds(new LatLng(-20.595001, -54.503947), new LatLng(-20.364035, -54.765508));

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.adicionar_foco_fragment, container, false);

        endereço = (AutoCompleteTextView) g.findViewById(R.id.ed);

       /* FloatingActionButton FAB = (FloatingActionButton) g.findViewById(R.id.fabFrag);


        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalizacaoAtual();
            }
        });
*/

        Main act = (Main) getActivity();
        endereço.setOnClickListener(mAutocompleteClickListener);

        mAdapter = new PlaceAutocompleteAdapter(act, android.R.layout.simple_list_item_1,
                act.getmGoogleApiClient(), BOUNS_CAMPO_GRANDE, null);
        endereço.setAdapter(mAdapter);


        return g;
    }

    private void LocalizacaoAtual() {

    }

    public AdicionarFoco() {
    }


    public interface OnFragmentInteractionListener {
    }
}