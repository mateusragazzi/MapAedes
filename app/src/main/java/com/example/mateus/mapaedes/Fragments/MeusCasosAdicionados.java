package com.example.mateus.mapaedes.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mateus.mapaedes.Adapters.AddAdapter;
import com.example.mateus.mapaedes.Adapters.BancoDeDados;
import com.example.mateus.mapaedes.R;

import java.util.ArrayList;

/**
 * Created by zazah on 27/02/2017.
 */

public class MeusCasosAdicionados extends Fragment {
    public static ListView lstDados;
    private ArrayList<String> nome= new ArrayList<>();
    private ArrayList<String> endereco = new ArrayList<>();
    private ArrayList<Integer> doenca = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.meus_casos_adicionados_fragment, container, false);

        lstDados = (ListView)v.findViewById(R.id.listView);

        BancoDeDados bd = new BancoDeDados(getActivity());
        SQLiteDatabase banco = bd.getReadableDatabase();
        lstDados.setAdapter(new AddAdapter(getActivity(), doenca, nome, endereco));


        Cursor cursor = banco.query("casos", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String pnome = cursor.getString(cursor.getColumnIndex("nomePessoaDoenca"));
                String pdoenca = cursor.getString(cursor.getColumnIndex("tipoDoenca"));
                String pendereco = cursor.getString(cursor.getColumnIndex("enderecoDoenca"));


//            Toast.makeText(getActivity(),pendereco, Toast.LENGTH_SHORT).show();

                switch (pdoenca){
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
                        Toast.makeText(getActivity(),pdoenca, Toast.LENGTH_SHORT).show();

                }
                nome.add(pnome +" - "+pdoenca);
                endereco.add(pendereco);
            } while (cursor.moveToNext());
        }

        return v;
    }



    public interface OnFragmentInteractionListener {
    }



    public MeusCasosAdicionados() {
    }


}
