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

public class Buscar extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.buscar_fragment, container, false);
        return g;
    }

    public Buscar() {
    }

    public interface OnFragmentInteractionListener {
    }
}
