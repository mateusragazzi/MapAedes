package com.example.mateus.mapaedes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mateus.mapaedes.R;

/**
 * Created by zazah on 27/02/2017.
 */

public class AdicionarFoco extends Fragment{

    public static EditText endereço;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.adicionar_foco_fragment, container, false);

        endereço = (EditText) g.findViewById(R.id.ed);

       /* FloatingActionButton FAB = (FloatingActionButton) g.findViewById(R.id.fabFrag);


        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalizacaoAtual();
            }
        });
*/

        return g;
    }

    private void LocalizacaoAtual() {

    }

    public AdicionarFoco() {
    }



    public interface OnFragmentInteractionListener {
    }
}