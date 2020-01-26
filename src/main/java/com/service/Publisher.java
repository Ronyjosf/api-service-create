package com.service;

import com.google.gson.JsonArray;

public class Publisher implements IData {
    public String publisherId;
    public String time;
    public int[] readings;
    private double median;

    @Override
    public void storeData(JsonArray publisherArr) {
//        publisher = publisherArr.publisher;
    }

    public Publisher(String publisherId, String time, int[] readings){
        this.publisherId = publisherId;
        this.time = time;
        this.readings = readings;
    }
    public Publisher(){
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setMedian() {
        median = Utils.getMedian(readings);
    }
    public double getMedian(){
        return median;
    }

}


