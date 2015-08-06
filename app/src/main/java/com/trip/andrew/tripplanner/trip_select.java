package com.trip.andrew.tripplanner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class trip_select extends AppCompatActivity {


    private List<Trip> trips;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_select);



        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        //rv.setHasFixedSize(true);
        db_trip dbt=new db_trip(getApplicationContext());
        initData();
        initializeAdapter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_Add_trip :
                Intent intent=new Intent(this,trip_add.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void initData()
    {
        db_trip dbt=new db_trip(getApplicationContext());

        trips=dbt.getAllTrips();
        if(trips.size()==0)
            trips.add(new Trip("Italy",300000000,400000000,""));
        //trips.add(new Trip("Spain",100000000,200000000,""));

        //Collections.sort(trips, new Trip.TripComparator());
    }

    private void initializeAdapter(){
        adapter_trip adapter = new adapter_trip(trips);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initData();
        initializeAdapter();


        //initData();

        //initializeAdapter();

    }

    public void onInsertElem(int pos)
    {
        //rv.getAdapter().notifyItemInserted(pos);
    }
}
