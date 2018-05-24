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
import com.example.hendawy.metro.adapter.LineThreeStationsAdapter;
import com.example.hendawy.metro.constants.Utils;
import com.example.hendawy.metro.helper.StationManger;
import com.example.hendawy.metro.model.Station;
import com.example.hendawy.metro.model.StationModel;

import java.util.ArrayList;


public class LineThree extends Fragment implements LineThreeStationsAdapter.ClickListener {
	
	private RecyclerView recyclerView;
	private ArrayList<Station> lineThreeStationsList;
	private LineThreeStationsAdapter adapter;
	StationManger stationManger;
	
	public LineThree() {
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
		lineThreeStationsList = StationModel.get(getActivity()).getLineThreeStations();
		adapter = new LineThreeStationsAdapter(getActivity(), lineThreeStationsList);
		adapter.setClickListenerForLineThree(this);
		recyclerView.setAdapter(adapter);
		return view;
	}
	
	@Override
	public void itemClicked(View view, int postion) {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		if (stationManger.getCurrentStationNumber() == 0) {
			stationManger.isUserSaveCurrentSession(true);
			stationManger.setCurrentStationNumber(Utils.lineThreeStationsWeights[postion]);
			stationManger.setCurrentStationName(Utils.lineThreeStationsNames[postion]);
		} else {
			intent.putExtra(Utils.TARGET_STATION_NO, Utils.lineThreeStationsWeights[postion]);
			intent.putExtra(Utils.TARGET_STATION_NAME, Utils.lineThreeStationsNames[postion]);
		}
		startActivity(intent);
	}
}
