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
import com.example.hendawy.metro.constants.Utils;
import com.example.hendawy.metro.helper.StationManger;
import com.example.hendawy.metro.model.Station;
import com.example.hendawy.metro.model.StationModel;

import java.util.ArrayList;



public class AllLineFragment extends Fragment implements AllStationsAdapter.ClickListener  {

    private RecyclerView recyclerView;
    private ArrayList<Station> allStationsList;
    private AllStationsAdapter adapter;
    StationManger stationManger;


    public AllLineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_line, container, false);

        stationManger = new StationManger(getActivity());
        recyclerView =  view.findViewById(R.id.stationList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allStationsList = StationModel.get(getActivity()).getAllLinesStations();
        adapter = new AllStationsAdapter(getActivity(), allStationsList);
        adapter.setClickListenerForAllStation(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void itemClicked(View view, int postion) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        if (stationManger.getCurrentStationNumber() == 0) {
            stationManger.isUserSaveCurrentSession(true);
            stationManger.setCurrentStationNumber(Utils.allStationsWeights[postion]);
            stationManger.setCurrentStationName(Utils.allStationsNames[postion]);
        } else {
            intent.putExtra(Utils.TARGET_STATION_NO, Utils.allStationsWeights[postion]);
            intent.putExtra(Utils.TARGET_STATION_NAME, Utils.allStationsNames[postion]);
        }
        startActivity(intent);

    }
}
