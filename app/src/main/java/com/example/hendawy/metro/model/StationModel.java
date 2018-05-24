package com.example.hendawy.metro.model;

import android.content.Context;

import java.util.ArrayList;

import static com.example.hendawy.metro.constants.Utils.allStationsNames;
import static com.example.hendawy.metro.constants.Utils.lineOneStationsNames;
import static com.example.hendawy.metro.constants.Utils.lineThreeStationsNames;
import static com.example.hendawy.metro.constants.Utils.lineTwoStationsNames;

public class StationModel {
	
	private static StationModel stationModel;
	private Context context;
	private ArrayList<Station> allStationsList = new ArrayList<>();
	private ArrayList<Station> lineOneStationsList = new ArrayList<>();
	private ArrayList<Station> lineTwoStationsList = new ArrayList<>();
	private ArrayList<Station> lineThreeStationsList = new ArrayList<>();
	
	
	public StationModel(Context context) {
		this.context = context;
	}
	
	public static StationModel get(Context context) {
			stationModel = new StationModel(context);
		return stationModel;
	}
	
	public ArrayList<Station> getAllLinesStations() {
		
		for (int i = 0; i < allStationsNames.length; i++) {
			Station station = new Station(allStationsNames[i]);
			allStationsList.add(station);
		}
		return allStationsList;
	}
	
	public ArrayList<Station> getLineOneStations() {
		
		for (int i = 0; i < lineOneStationsNames.length; i++) {
			Station station = new Station(lineOneStationsNames[i]);
			lineOneStationsList.add(station);
		}
		return lineOneStationsList;
	}
	
	public ArrayList<Station> getLineTwoStations() {
		
		for (int i = 0; i < lineTwoStationsNames.length; i++) {
			Station station = new Station(lineTwoStationsNames[i]);
			lineTwoStationsList.add(station);
		}
		return lineTwoStationsList;
	}
	
	public ArrayList<Station> getLineThreeStations() {
		
		for (int i = 0; i < lineThreeStationsNames.length; i++) {
			Station station = new Station(lineThreeStationsNames[i]);
			lineThreeStationsList.add(station);
		}
		return lineThreeStationsList;
	}
	
}
