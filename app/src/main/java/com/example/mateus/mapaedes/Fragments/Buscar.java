package com.example.mateus.mapaedes.Fragments;

import android.content.Intent;
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

import com.example.mateus.mapaedes.Activities.resultadoBusca;
import com.example.mateus.mapaedes.Adapters.ListaAdapter;
import com.example.mateus.mapaedes.R;
import com.example.mateus.mapaedes.helpers.Disease;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zazah on 08/08/2016.
 */
public class Buscar extends Fragment implements View.OnClickListener {

    private CheckBox  checkBox2, checkBox3;
    public EditText etENDERECO;
    public static int a = 0, b = 0, c = 0, a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, tipo = 0, endereco = 0;
    public RadioButton dengue, zika, chikungunya, guillain_barre, nyong_nyong;
    public static ArrayList<ListaAdapter> result = new ArrayList<>();
    public ArrayList<String> lista = new ArrayList<>();
    public static String  etendereco;


    List<Disease> diseases = Disease.listAll(Disease.class);

    private String name, disease, address;
    private double lat, lng;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.buscar_fragment, container, false);


        checkBox2 = (CheckBox) g.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) g.findViewById(R.id.checkBox3);


        dengue = (RadioButton) g.findViewById(R.id.radioButton2);
        zika = (RadioButton) g.findViewById(R.id.radioButton3);
        chikungunya = (RadioButton) g.findViewById(R.id.radioButton4);
        guillain_barre = (RadioButton) g.findViewById(R.id.radioButton5);
        nyong_nyong = (RadioButton) g.findViewById(R.id.radioButton6);


        etENDERECO = (EditText) g.findViewById(R.id.etEndereco);


        etENDERECO.setVisibility(View.INVISIBLE);


        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    dengue.setEnabled(true);
                    zika.setEnabled(true);
                    chikungunya.setEnabled(true);
                    guillain_barre.setEnabled(true);
                    nyong_nyong.setEnabled(true);
                    b = 1;
                } else {
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

                    b = 0;
                }

            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    etENDERECO.setVisibility(View.VISIBLE);
                    c = 1;
                } else {
                    etENDERECO.setVisibility(View.INVISIBLE);
                    c = 0;
                }
            }
        });

        dengue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dengue.isChecked()) {

                    if (a1 == 1) {
                        dengue.setChecked(false);
                        // Toast.makeText(getActivity(), a1, Toast.LENGTH_SHORT).show();
                        a1 = 0;
                    } else {
                        dengue.setChecked(true);
                        a1 = 1;
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
        BUSCAR.setOnClickListener(new View.OnClickListener() {

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
                        // i 0 ou 1
                        for (int j = 0; j < diseases.size(); j++) {
                            //name = diseases.get(j).getNameUser();
                            disease = diseases.get(j).getDisease();
                            address = diseases.get(j).getAddress();
                            lat = diseases.get(j).getLat();
                            lng = diseases.get(j).getLng();

                            if (disease.equals(nome)) {
                                if (endereco == 0) {
                                    ListaAdapter listaAdapter = new ListaAdapter(disease, address, lat, lng, name);
                                    result.add(listaAdapter);
                                    // Toast.makeText(getActivity(),"oi", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (address.contains(etendereco)) {
                                        ListaAdapter listaAdapter = new ListaAdapter(disease, address, lat, lng, name);
                                        result.add(listaAdapter);
                                    }
                                }
                            }
                        }


                    }
                } else if (endereco == 1) {
                    Endereco();
                }
                Busca();

                Intent intent = new Intent(getActivity(), resultadoBusca.class);
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

        for (int j = 0; j < diseases.size(); j++) {
            //name = diseases.get(j).getNameUser();
            disease = diseases.get(j).getDisease();
            address = diseases.get(j).getAddress();
            lat = diseases.get(j).getLat();
            lng = diseases.get(j).getLng();

            if (address.contains(etendereco)) {
                ListaAdapter listaAdapter = new ListaAdapter(disease, address, lat, lng, name);
                result.add(listaAdapter);
            }

        }
    }


    public void Busca() {
        if (result.size() == 0) {
            Toast.makeText(getActivity(), "Nenhum resultado obtido", Toast.LENGTH_SHORT).show();

        }
    }
}


