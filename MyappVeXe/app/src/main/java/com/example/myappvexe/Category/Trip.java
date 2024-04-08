package com.example.myappvexe.Category;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Trip implements Serializable {
    private long id;
    private String locationStar ;
    private String locationEnd ;
    private String dateBusStar ;
    private String timeBusStar ;
    private String timeBusEnd ;
    private String timeEnd ;
    private String priceTrip ;
    private String seats ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationStar() {
        return locationStar;
    }

    public void setLocationStar(String locationStar) {
        this.locationStar = locationStar;
    }

    public String getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        this.locationEnd = locationEnd;
    }

    public String getDateBusStar() {
        return dateBusStar;
    }

    public void setDateBusStar(String dateBusStar) {
        this.dateBusStar = dateBusStar;
    }

    public String getTimeBusStar() {
        return timeBusStar;
    }

    public void setTimeBusStar(String timeBusStar) {
        this.timeBusStar = timeBusStar;
    }

    public String getTimeBusEnd() {
        return timeBusEnd;
    }

    public void setTimeBusEnd(String timeBusEnd) {
        this.timeBusEnd = timeBusEnd;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getPriceTrip() {
        return priceTrip;
    }

    public void setPriceTrip(String priceTrip) {
        this.priceTrip = priceTrip;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @NonNull
    @Override
    public String toString(){
        return locationStar + "\n" + locationEnd + "\n" + dateBusStar + "\n" +
                timeBusStar  + "\n" + timeBusEnd + "\n" + timeEnd + "\n" +
                priceTrip + "\n" + seats;
    }
}
