package com.example.mateus.mapaedes.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateus.mapaedes.R;

/**
 * Created by zazah on 27/02/2017.
 */

public class Informacoes extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.informacoes_fragment, container, false);
        return g;
    }

    public Informacoes() {
    }

    public interface OnFragmentInteractionListener {
    }
}
