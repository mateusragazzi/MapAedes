package com.example.mateus.mapaedes.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateus.mapaedes.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchGraph.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchGraph#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchGraph extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LineChart mLineChart;
    private LineDataSet mLineDataSet;
    private ArrayList<String> mLegenda = new ArrayList<>();
    private List<ILineDataSet> info = new ArrayList<>();
    private ArrayList<Entry> mInformation = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public SearchGraph() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchGraph.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchGraph newInstance(String param1, String param2) {
        SearchGraph fragment = new SearchGraph();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_graph, container, false);
        mLineChart = (LineChart) view.findViewById(R.id.grafico);
        inicializaGraph();
        return view;
    }

    public void inicializaGraph() {
        //add um novo dado
        //Se for add vários dados, favor colocar isso dentro de uma método
        mInformation.add(new Entry(8, 0));
        //relaciona o dado com uma legenda
        mLegenda.add("Legenda");
        info.clear();
        YAxis y = mLineChart.getAxisLeft();
        y.setAxisMinValue(0);
        y.setAxisMaxValue(10);
        y.setDrawGridLines(true);
        YAxis y1 = mLineChart.getAxisRight();
        y1.setEnabled(false);

        XAxis x = mLineChart.getXAxis();
        x.setSpaceBetweenLabels(2);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.setTouchEnabled(false);

        //passa as informações para o grafico
        mLineDataSet = new LineDataSet(mInformation, "Title");
        mLineDataSet.setColor(Color.RED);
        mLineDataSet.setLineWidth(2.5f);
        mLineDataSet.setCircleColor(Color.RED);
        mLineDataSet.setDrawCubic(false);
        mLineDataSet.setDrawValues(false);

        info.add(mLineDataSet);
        LineData l = new LineData(mLegenda, info);
        mLineChart.setData(l);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
