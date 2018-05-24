package com.example.hendawy.metro.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.metro.R;
import com.example.hendawy.metro.activites.MainActivity;
import com.example.hendawy.metro.adapter.AllStationsAdapter;
import com.example.hendawy.metro.adapter.LineOneStationsAdapter;
import com.example.hendawy.metro.constants.Utils;
import com.example.hendawy.metro.helper.StationManger;
import com.example.hendawy.metro.model.Station;
import com.example.hendawy.metro.model.StationModel;

import java.util.ArrayList;



public class LineOne extends Fragment implements LineOneStationsAdapter.ClickListener{

    private RecyclerView recyclerView;
    private ArrayList<Station> lineOneStationsList;
    private LineOneStationsAdapter adapter;
    StationManger stationManger;


    public LineOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_line_one, container, false);
        
        stationManger = new StationManger(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.stationList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        lineOneStationsList = StationModel.get(getActivity()).getLineOneStations();
        adapter = new LineOneStationsAdapter(getActivity(), lineOneStationsList);
        adapter.setClickListenerForLineOne(this);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void itemClicked(View view, int postion) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        if (stationManger.getCurrentStationNumber() == 0) {
            stationManger.isUserSaveCurrentSession(true);
            stationManger.setCurrentStationNumber(Utils.lineOneStationsWeights[postion]);
            stationManger.setCurrentStationName(Utils.lineOneStationsNames[postion]);
        } else {
            intent.putExtra(Utils.TARGET_STATION_NO, Utils.lineOneStationsWeights[postion]);
            intent.putExtra(Utils.TARGET_STATION_NAME, Utils.lineOneStationsNames[postion]);
        }
        startActivity(intent);

    }
}
