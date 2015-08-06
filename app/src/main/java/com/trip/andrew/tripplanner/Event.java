package com.trip.andrew.tripplanner;

/**
 * Created by Andrew on 05.08.2015.
 */
public class Event{
    int id;
    String name;
    int visited;

    long startDate,endDate;

    int type;

    String iconName;

    private static final int Visited = 1;
    private static final int Unvisited = 0;
    //Constructors
    public Event()
    {
        visited=Event.Unvisited;
    }
    public Event(int id,int type,String name,long startDate,long endDate,int visited)
    {
        this.id=id;
        this.type=type;
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
        this.visited=Event.Unvisited;
    }

    //setters
    public void setIconName(String iconName)
    {
        this.iconName=iconName;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public void setStartDate(long startDate)
    {
        this.startDate=startDate;
    }
    public void setEndDate(long endDate)
    {
        this.endDate=endDate;
    }
    public void setType(int type)
    {
        this.type=type;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setVisited(int visited)
    {
        if(visited!=Event.Unvisited)
           this.visited=Event.Visited;
        else
           this.visited=Event.Unvisited;

    }

    //getters

    long getStartDate()
    {
        return this.startDate;
    }
    long getEndDate()
    {
        return this.endDate;
    }

    int getType()
    {
        return this.type;
    }

    String getName()
    {
        return this.name;
    }
    String getIconName()
    {
        return this.iconName;
    }

}
