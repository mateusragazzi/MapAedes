package com.example.mateus.mapaedes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mateus.mapaedes.Adapters.AddAdapter;
import com.example.mateus.mapaedes.R;
import com.example.mateus.mapaedes.helpers.Disease;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zazah on 27/02/2017.
 */

public class MeusCasosAdicionados extends Fragment {
    public static ListView lstDados;
    private ArrayList<String> nome = new ArrayList<>();
    private ArrayList<String> endereco = new ArrayList<>();
    private ArrayList<Integer> doenca = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.meus_casos_adicionados_fragment, container, false);

        lstDados = (ListView) v.findViewById(R.id.listView);

        lstDados.setAdapter(new AddAdapter(getActivity(), doenca, nome, endereco));


        List<Disease> disease = Disease.listAll(Disease.class);

        for (int i = 0; i < disease.size(); i++) {
            String name = disease.get(i).getNameUser();
            String diseases = disease.get(i).getDisease();
            String address = disease.get(i).getAddress();

            switch (diseases) {
                case "Dengue":
                    doenca.add(R.mipmap.red);
                    break;
                case "Zika vírus":
                    doenca.add(R.mipmap.green);
                    break;
                case "Chikungunya":
                    doenca.add(R.mipmap.blue);
                    break;
                case "Guillain barré":
                    doenca.add(R.mipmap.yellow);
                    break;
                case "Nyongnyong":
                    doenca.add(R.mipmap.orange);
                    break;
                default:
                    doenca.add(R.mipmap.point);

            }
            nome.add(name+ " - " + diseases);
            endereco.add(address);
        }


        return v;
    }


    public interface OnFragmentInteractionListener {
    }


    public MeusCasosAdicionados() {
    }


}
