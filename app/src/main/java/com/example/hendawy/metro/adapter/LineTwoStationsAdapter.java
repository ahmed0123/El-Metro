package com.example.hendawy.metro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hendawy.metro.R;
import com.example.hendawy.metro.activites.MainActivity;
import com.example.hendawy.metro.constants.Utils;
import com.example.hendawy.metro.constants.VectorDrawableUtils;
import com.example.hendawy.metro.helper.StationManger;
import com.example.hendawy.metro.model.Station;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;

public class LineTwoStationsAdapter extends RecyclerView.Adapter<LineTwoStationsAdapter.LineTwoStationViewHolder> {

    Context context;
    ArrayList<Station> stationList;
    StationManger stationManger;
    private ClickListener clickListener;


    public LineTwoStationsAdapter(Context context, ArrayList<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    @Override
    public LineTwoStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.station_item, parent, false);
        return new LineTwoStationsAdapter.LineTwoStationViewHolder(view , viewType);
    }

    @Override
    public void onBindViewHolder(LineTwoStationViewHolder holder, int position) {
    
        Station station = stationList.get(position);
        if (station.getName()== Utils.lineTwoStationsNames[0] || station.getName() == Utils.lineTwoStationsNames[19]){
            holder.mTimelineView.setMarker(ContextCompat.getDrawable(context, R.drawable.ic_marker), ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(context, R.drawable.ic_marker_inactive, R.color.colorPrimary));
        }
        holder.stationName.setText(station.getName());
    }

    public void setClickListenerForLineTwo(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
    

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class LineTwoStationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stationName;
        TimelineView mTimelineView;

        public LineTwoStationViewHolder(View itemView , int viewType) {
            super(itemView);
            stationName = itemView.findViewById(R.id.stationName);
            mTimelineView = itemView.findViewById(R.id.marker);
            mTimelineView.initLine(viewType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int postion);
    }
}

