package com.trip.andrew.tripplanner;

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

/**
 * Created by Andrew on 06.08.2015.
 */
public class adapter_event extends RecyclerView.Adapter<adapter_event.EventViewHolder> {
    public static class EventViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv_trip;
        TextView tripName;
        TextView tripDate;
        ImageView tripIcon;

        EventViewHolder(final View itemView) {
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

    List<Event> events;
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_trip, viewGroup, false);
        EventViewHolder tvh = new EventViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder tripViewHolder, int i) {

        //converting milliseconds to date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        tripViewHolder.tripName.setText(events.get(i).name);

        tripViewHolder.tripDate.setText(formatter.format(new Date(events.get(i).getStartDate()))+" - "+formatter.format(new Date(events.get(i).getEndDate())));
        if(events.get(i).getIconName()=="")
            tripViewHolder.tripIcon.setImageResource(R.drawable.trip_icon_green);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
