package com.example.hp.ieeepec;

public class Notification {
    private String title, desc, date, time, venue;
    private int logo;

    public Notification(String title, String desc, String date, String time, String venue, int logo) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public int getLogo() {
        return logo;
    }
}
