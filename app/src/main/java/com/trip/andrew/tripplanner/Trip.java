package com.trip.andrew.tripplanner;

import java.util.Comparator;

/**
 * Created by Andrew on 29.07.2015.
 */
//for storing elems with TRIP table from SQLite
public class Trip {
    public static class TripComparator implements Comparator<Trip> {
        @Override
        public int compare(Trip t1,Trip t2)
        {
            if(t1.getStartDate()>t2.getStartDate())
                return 1;
            if(t1.getStartDate()==t2.getStartDate())
                return 0;
            return -1;
        }
    }
    long id;
    String name;
    long startDate;
    long endDate;
    String iconName;

    //constructors
    public Trip()
    {}

    public Trip(String name, long startDate, long endDate,String iconName)
    {
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
        this.iconName=iconName;
    }

    public Trip(long id, String name, long startDate, long endDate,String iconName)
    {
        this.id=id;
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
        this.iconName=iconName;
    }

    //getters
    long getId()
    {
        return this.id;
    }

    String getName()
    {
        return this.name;
    }

    long getStartDate()
    {
        return this.startDate;
    }

    long getEndDate()
    {
        return this.endDate;
    }

    String getIconName()
    {
        return this.iconName;
    }

    //setters

    void setId(long id)
    {
        this.id=id;
    }

    void setName(String name)
    {
        this.name=name;
    }

    void  setStartDate(long startDate)
    {
        this.startDate=startDate;
    }

    void setEndDate(long endDate)
    {
        this.endDate=endDate;
    }

    void setIconName(String iconName)
    {
        this.iconName=iconName;
    }
}
