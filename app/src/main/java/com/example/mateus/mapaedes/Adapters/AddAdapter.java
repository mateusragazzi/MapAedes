package com.example.mateus.mapaedes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateus.mapaedes.R;

import java.util.ArrayList;

/**
 * Created by HP PAVILION on 04/04/2016.
 */
public class AddAdapter extends BaseAdapter {

        public ArrayList<String> nome = new ArrayList<>();
        public ArrayList<Integer> doenca = new ArrayList<>();
        public ArrayList<String> endereco = new ArrayList<>();
        private Context ctx;
        public TextView nomee, enderecoo;
        public ImageView img;

        public AddAdapter(Context context, ArrayList<Integer> doenca, ArrayList<String> nome, ArrayList<String> endereco) {
            this.ctx = context;
            this.nome = nome;
            this.doenca = doenca;
            this.endereco = endereco;

        }

        @Override
        public int getCount() {
            return nome.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_item, parent, false);
            img = (ImageView)v.findViewById(R.id.imageVie);
            nomee = (TextView)v.findViewById(R.id.NomeList);
            enderecoo = (TextView)v.findViewById(R.id.enderecoList);
            img.setImageResource(doenca.get(position));
            nomee.setText(nome.get(position));
            enderecoo.setText(endereco.get(position));

            return v;
        }
    }



