package com.trip.andrew.tripplanner;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class trip_add extends AppCompatActivity {

    Trip cur;
    public void setCurStart(long start)
    {
        cur.setStartDate(start);
    }

    public void setCurEnd(long end)
    {
        cur.setEndDate(end);
    }
    public void setCurName(String name)
    {
        cur.setName(name);
    }
    int flag;
    public static class DatePickerFragment extends DialogFragment
           implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        //@Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            //Do something with the date chosen by the user
            TextView tv;
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH,day);
            cal.set(Calendar.MONTH,month);
            cal.set(Calendar.YEAR,year);
            long msec=cal.getTimeInMillis();
            if(((trip_add)getActivity()).flag==1) {
                tv = (TextView) getActivity().findViewById(R.id.tv_trip_date_start);
                ((trip_add) getActivity()).setCurStart(msec);
            }
            else {
                tv = (TextView) getActivity().findViewById(R.id.tv_trip_date_end);
                ((trip_add) getActivity()).setCurEnd(msec);
            }
            String stringOfDate = day + "." + month + "." + year;
            tv.setText(stringOfDate);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_add);
        cur = new Trip();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        TextView tv = (TextView) findViewById(R.id.tv_trip_date_start);
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        tv.setText(formatter.format(d));//date of start
        cur.setStartDate(cal.getTimeInMillis());
        ////end date
        tv = (TextView) findViewById(R.id.tv_trip_date_end);//change tv field
        cal.add(Calendar.DAY_OF_MONTH, 1);//add 1 day
        d = cal.getTime();//date of end
        tv.setText(formatter.format(d));
        cur.setEndDate(cal.getTimeInMillis());
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(trip_add.this, trip_select.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialogStart(View v)
    {
        DatePickerFragment nFrag = new DatePickerFragment();
        nFrag.show(getSupportFragmentManager(), "datePicker");
        flag=1;


    }

    public void showDatePickerDialogEnd(View v)
    {
        DatePickerFragment nFrag = new DatePickerFragment();
        nFrag.show(getSupportFragmentManager(), "datePicker");
        flag=2;


    }

    public void onAddTrip(View v)
    {
        //Toast.makeText(getApplicationContext(), "ntcn", Toast.LENGTH_SHORT).show();
        EditText et=(EditText)findViewById(R.id.et_trip_add_name);
        if(et.getText().length()==0)
        {
            Toast.makeText(getApplicationContext(), "������ ������ ���", Toast.LENGTH_SHORT).show();
        }
        else {
            cur.setName(et.getText().toString());
            cur.setIconName(" ");
            db_trip dbt = new db_trip(getApplicationContext());

            dbt.insertTrip(cur);
            finish();
        }
    }

    public void onBack(View v)
    {
        finish();
    }

}
