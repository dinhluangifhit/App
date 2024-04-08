package com.example.myappvexe.Location;

public class Location {
    private long id;
    private String nameLocation;
    private String busStation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getBusStation() {
        return busStation;
    }

    public void setBusStation(String busStation) {
        this.busStation = busStation;
    }

    @Override
    public String toString(){
        return nameLocation + "\n" + busStation;
    }
}
