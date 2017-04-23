package com.example.mateus.mapaedes.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mateus.mapaedes.Adapters.BancoDeDados;
import com.example.mateus.mapaedes.Adapters.ListaAdapter;
import com.example.mateus.mapaedes.R;

import java.util.ArrayList;

/**
 * Created by zazah on 08/08/2016.
 */
public class Buscar extends Fragment implements View.OnClickListener {

    private CheckBox checkBox, checkBox2, checkBox3;
    public  EditText  etENDERECO;
    public  int a=0, b=0, c=0, a1 =0, a2 = 0, a3=0, a4=0, a5=0, tipo=0, endereco=0;
    public RadioButton  dengue, zika, chikungunya, guillain_barre, nyong_nyong;
    public ArrayList<ListaAdapter> result = new ArrayList<>();
    public ArrayList<String> lista = new ArrayList<>();
    public String doencaP, enderecoP, etendereco, nomeP;
    public double Plat1, Plng2;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.buscar_fragment, container, false);



        checkBox2 = (CheckBox) g.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) g.findViewById(R.id.checkBox3);



        dengue = (RadioButton)g.findViewById(R.id.radioButton2);
        zika = (RadioButton)g.findViewById(R.id.radioButton3);
        chikungunya = (RadioButton)g.findViewById(R.id.radioButton4);
        guillain_barre = (RadioButton)g.findViewById(R.id.radioButton5);
        nyong_nyong = (RadioButton)g.findViewById(R.id.radioButton6);


        etENDERECO = (EditText)g.findViewById(R.id.etEndereco);


        etENDERECO.setVisibility(View.INVISIBLE);


        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()){
                    dengue.setEnabled(true);
                    zika.setEnabled(true);
                    chikungunya.setEnabled(true);
                    guillain_barre.setEnabled(true);
                    nyong_nyong.setEnabled(true);
                    b=1;
                }else{
                    dengue.setEnabled(false);
                    zika.setEnabled(false);
                    chikungunya.setEnabled(false);
                    guillain_barre.setEnabled(false);
                    nyong_nyong.setEnabled(false);

                    dengue.setChecked(false);
                    zika.setChecked(false);
                    chikungunya.setChecked(false);
                    guillain_barre.setChecked(false);
                    nyong_nyong.setChecked(false);

                    b=0;
                }

            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    etENDERECO.setVisibility(View.VISIBLE);
                    c=1;
                } else {
                    etENDERECO.setVisibility(View.INVISIBLE);
                    c=0;
                }
            }
            });

          dengue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dengue.isChecked()) {

                    if (a1 == 1){
                        dengue.setChecked(false);
                        // Toast.makeText(getActivity(), a1, Toast.LENGTH_SHORT).show();
                        a1 = 0;
                    }else{
                        dengue.setChecked(true);
                        a1=1;
                        // Toast.makeText(getActivity(), a1, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        zika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zika.isChecked()) {
                    if (a2 == 1) {
                        zika.setChecked(false);
                        a2 = 0;
                    } else {
                        zika.setChecked(true);
                        a2 = 1;
                    }
                }
            }
        });
        chikungunya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chikungunya.isChecked()) {
                    if (a3 == 1) {
                        chikungunya.setChecked(false);
                        a3 = 0;
                    } else {
                        chikungunya.setChecked(true);
                        a3 = 1;
                    }
                }
            }
        });
        guillain_barre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guillain_barre.isChecked()) {
                    if (a4 == 1) {
                        guillain_barre.setChecked(false);
                        a4 = 0;
                    } else {
                        guillain_barre.setChecked(true);
                        a4 = 1;
                    }
                }
            }
        });
        nyong_nyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nyong_nyong.isChecked()) {
                    if (a5 == 1) {
                        nyong_nyong.setChecked(false);
                        a5 = 0;
                    } else {
                        nyong_nyong.setChecked(true);
                        a5 = 1;
                    }
                }
            }
        });



        Button BUSCAR = (Button) g.findViewById(R.id.buscar);
        BUSCAR.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                result.clear();
                lista.clear();
                tipo = 0;
                endereco = 0;

                if (checkBox2.isChecked()) {
                    tipo = 1;
                    if (dengue.isChecked()) {
                        lista.add("Dengue");
                    }
                    if (zika.isChecked()) {
                        lista.add("Zika vírus");
                    }
                    if (chikungunya.isChecked()) {
                        lista.add("Chikungunya");
                    }
                    if (guillain_barre.isChecked()) {
                        lista.add("Guillain barre");
                    }
                    if (nyong_nyong.isChecked()) {
                        lista.add("Nyongnyong");
                    }
                }

                if (checkBox3.isChecked()) {
                    endereco = 1;
                    etendereco = etENDERECO.getText().toString().toLowerCase();


                }

                if (tipo == 1) {
                    for (int i = 0; i < lista.size(); i++) {
                        String nome = lista.get(i);
                        //Toast.makeText(getActivity(),nome, Toast.LENGTH_SHORT).show();

                        BancoDeDados bd = new BancoDeDados(getActivity());
                        SQLiteDatabase banco = bd.getReadableDatabase();
                        Cursor cursore = banco.query("casos", null, null, null, null, null, null);
                        if (cursore.moveToFirst()) {
                            do{
                                nomeP = cursore.getString(cursore.getColumnIndex("nomePessoaDoenca"));
                                doencaP = cursore.getString(cursore.getColumnIndex("tipoDoenca"));
                                enderecoP = cursore.getString(cursore.getColumnIndex("enderecoDoenca")).toLowerCase();
                                Plat1 = cursore.getDouble(cursore.getColumnIndex("latDoenca"));
                                Plng2 = cursore.getDouble(cursore.getColumnIndex("lngDoenca"));

                                if (doencaP.equals(nome)) {
                                    if (endereco == 0) {
                                        ListaAdapter listaAdapter = new ListaAdapter (doencaP, enderecoP, Plat1, Plng2);
                                        result.add(listaAdapter);
                                        // Toast.makeText(getActivity(),"oi", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (enderecoP.contains(etendereco)){
                                           ListaAdapter listaAdapter = new ListaAdapter (doencaP, enderecoP, Plat1, Plng2);
                                            result.add(listaAdapter);
                                        }
                                    }
                                }

                            }while (cursore.moveToNext());
                        }


                    }
                }else if (endereco == 1){
                 Endereco();
                }
                Busca();

                /*

                ABRIR OS RESULTADOS DAS TABS, AGORA ESTA ABRINDO UMA CLASSE QUE EU TINHA CRIADO PARA FAZER AS TABS, MAS VOCE
                CRIOU NOVASSSS

                */

                Intent intent = new Intent(getActivity(),resultadoComLista.class);
                startActivity(intent);
            }

            });







        return g;
    }



    @Override
    public void onClick(View v) {

    }

    public interface OnFragmentInteractionListener {
    }

    public void Endereco() {

        BancoDeDados bd = new BancoDeDados(getActivity());
        SQLiteDatabase banco = bd.getReadableDatabase();
        Cursor cursore = banco.query("Casos", null, null, null, null, null, null);
        if (cursore.moveToFirst()) {
            do {
                String nomeP = cursore.getString(cursore.getColumnIndex("nomePessoaDoenca"));
                String  doencaP = cursore.getString(cursore.getColumnIndex("tipoDoenca"));
                String enderecoP = cursore.getString(cursore.getColumnIndex("enderecoDoenca")).toLowerCase();
                Double Plat1 = cursore.getDouble(cursore.getColumnIndex("latDoenca"));
                 Double Plng2 = cursore.getDouble(cursore.getColumnIndex("lngDoenca"));

                if (enderecoP.contains(etendereco)){
                    ListaAdapter listaAdapter = new ListaAdapter(doencaP, enderecoP, Plat1, Plng2);
                    result.add(listaAdapter);
                }
            } while (cursore.moveToNext());
        }
    }



    public void Busca(){
        for (int i = 0; i < result.size(); i++) {
            String resultado =  result.get(i).getTipo() + result.get(i).getEndereco();
            Toast.makeText(getActivity(),resultado, Toast.LENGTH_SHORT).show();
        }

        if (result.size() == 0){
            Toast.makeText(getActivity(),"Nenhum resultado obtido", Toast.LENGTH_SHORT).show();

        }
    }
    }


