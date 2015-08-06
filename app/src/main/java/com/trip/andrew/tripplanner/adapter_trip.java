package com.trip.andrew.tripplanner;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Andrew on 01.08.2015.
 */
public class adapter_trip extends RecyclerView.Adapter<adapter_trip.TripViewHolder> {
    public static class TripViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv_trip;
        TextView tripName;
        TextView tripDate;
        ImageView tripIcon;

        TripViewHolder(final View itemView) {
            super(itemView);
            cv_trip = (CardView)itemView.findViewById(R.id.cv_trip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Intent intent = new Intent(v.getContext(), SecondPage.class);
                    //v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), tripName.getText(), Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(itemView.getP,trip_details.class);
                    //startActivity(intent);
                }
            });
            tripName = (TextView)itemView.findViewById(R.id.trip_name);
            tripDate = (TextView)itemView.findViewById(R.id.trip_dates);
            tripIcon = (ImageView)itemView.findViewById(R.id.trip_photo);
        }
    }
    List<Trip> trips;
    adapter_trip(List<Trip> trips){
        this.trips = trips;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_trip, viewGroup, false);
        TripViewHolder tvh = new TripViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TripViewHolder tripViewHolder, int i) {

        //converting milliseconds to date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        tripViewHolder.tripName.setText(trips.get(i).name);

        tripViewHolder.tripDate.setText(formatter.format(new Date(trips.get(i).getStartDate()))+" - "+formatter.format(new Date(trips.get(i).getEndDate())));
        if(trips.get(i).getIconName()=="")
            tripViewHolder.tripIcon.setImageResource(R.drawable.trip_icon_green);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

}
