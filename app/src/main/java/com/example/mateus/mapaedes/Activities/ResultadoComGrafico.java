package com.example.mateus.mapaedes.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mateus.mapaedes.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class ResultadoComGrafico extends AppCompatActivity {
    private int[] setColors = new int[]{Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW, Color.BLUE, Color.BLACK};
    private int[] cont = new int[]{0,0,0,0,0,0};
    private ArrayList<String> mDoencas = new ArrayList<>();
    private BarChart mBarChart;
    private BarDataSet mBarDataSet;
    private ArrayList<String> mLegenda = new ArrayList<>();
    private List<IBarDataSet> info = new ArrayList<>();
    private ArrayList<BarEntry> mInformation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_com_grafico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        mDoencas = bundle.getStringArrayList("Doencas");
        openGraph();
    }

    private void openGraph() {
        info.clear();
        mBarChart = (BarChart) findViewById(R.id.grafico);
        for (int i = 0; i < mDoencas.size(); i++){
            String str = mDoencas.get(i);
            int index = str.indexOf("-");
            String doenca = str.substring(index + 1).trim();
            switch (doenca) {
                case "Dengue":
                    cont[0] = cont[0] + 1;
                    break;
                case "Zika vírus":
                    cont[1] = cont[1] + 1;
                    break;
                case "Chikungunya":
                    cont[2] = cont[2] + 1;
                    break;
                case "Guillain barré":
                    cont[3] = cont[3] + 1;
                    break;
                case "Nyongnyong":
                    cont[4] = cont[4] + 1;
                    break;
                case "Foco":
                    cont[5] = cont[5] + 1;
                    break;
                default:
            }
        }

        for (int i = 0; i < cont.length; i++) {
            int color = 0;
            String doenca = null;
            switch (i) {
                case 0:
                    doenca = "Dengue";
                    break;
                case 1:
                    doenca = "Zika";
                    break;
                case 2:
                    doenca = "Chikungunya";
                    break;
                case 3:
                    doenca = "Guillain";
                    break;
                case 4:
                    doenca = "Nyongnyong";
                    break;
                default:
                    color = Color.BLACK;
                    doenca = "Foco";
            }
            mInformation.add(new BarEntry(Float.parseFloat(String.valueOf(cont[i])), i));
            mLegenda.add(doenca);
        }
        mBarDataSet = new BarDataSet(mInformation, "Doenças");
        mBarDataSet.setColors(setColors);
        //info.add(mBarDataSet);
        //relaciona o dado com uma legenda
//
        YAxis y = mBarChart.getAxisLeft();
        y.setAxisMinValue(0);
        y.setAxisMaxValue(10);
        y.setDrawGridLines(true);
        YAxis y1 = mBarChart.getAxisRight();
        y1.setEnabled(false);
//
        XAxis x = mBarChart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setSpaceBetweenLabels(1);
//        mBarChart.setTouchEnabled(false);

        BarData l = new BarData(mLegenda, mBarDataSet);
        mBarChart.setData(l);
        mBarChart.setDescription("");
    }

    /*
    * Dengue: vermelho
    * Zika: verde
    * Nyon :  laranja
    * Guillant: amarelo
    * Chicun: azul
    * */

}
