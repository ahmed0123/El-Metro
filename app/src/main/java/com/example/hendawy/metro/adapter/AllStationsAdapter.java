package com.example.hendawy.metro.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hendawy.metro.R;
import com.example.hendawy.metro.constants.Utils;
import com.example.hendawy.metro.constants.VectorDrawableUtils;
import com.example.hendawy.metro.model.Station;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;

public class AllStationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Station> stationList, filterList;
    private ClickListener clickListener;
    private final static int LINE = 55;

    public AllStationsAdapter(Context context, ArrayList<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
        this.filterList = stationList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LINE) {
            return new LineViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.line_item, parent, false));
        } else {

            return new StationViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.station_item, parent, false), viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        if (viewType == LINE) {
            LineViewHolder lineViewHolder = (LineViewHolder) holder;
            switch (position ){
                case 0 :
                    lineViewHolder.lineName.setText("Line One");
                    break;
                case 36 :
                    lineViewHolder.lineName.setText("Line Two");
                    break;
                case 57 :
                    lineViewHolder.lineName.setText("Line Three");
            }

        } else {
            StationViewHolder stationViewHolder = (StationViewHolder) holder;
            Station station = stationList.get(position);

            if (station.getName() == Utils.allStationsNames[1]  || station.getName() == Utils.allStationsNames[19] ||
                    station.getName() == Utils.allStationsNames[22] || station.getName() == Utils.allStationsNames[58] ||
                    station.getName() == Utils.allStationsNames[44]||station.getName() == Utils.allStationsNames[45] || station.getName() == Utils.allStationsNames[35]
                    || station.getName() == Utils.allStationsNames[37]) {
                stationViewHolder.mTimelineView.setMarker(ContextCompat.getDrawable(context, R.drawable.ic_marker), ContextCompat.getColor(context, R.color.colorPrimary));

            } else {
                stationViewHolder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(context, R.drawable.ic_marker_inactive, R.color.colorPrimary));
            }

            stationViewHolder.stationName.setText(station.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 36 || position ==57) {
            return LINE;
        } else {
            return TimelineView.getTimeLineViewType(position, getItemCount());
        }
    }


    public void setClickListenerForAllStation(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }


    public class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stationName;
        TimelineView mTimelineView;

        public StationViewHolder(View itemView, int viewType) {
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

    public class LineViewHolder extends RecyclerView.ViewHolder {

        TextView lineName;

        public LineViewHolder(View itemView) {
            super(itemView);
            lineName = itemView.findViewById(R.id.lineNo);
        }
    }

}
