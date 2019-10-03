package com.techease.enaexblaster.models;

public class VibrationModel {
    String distance;
    String mic;
    String scallingFactor;
    String attenuation;
    String row_name;
    String checkCalculator;

    public String getRow_name() {
        return row_name;
    }

    public void setRow_name(String row_name) {
        this.row_name = row_name;
    }



    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getScallingFactor() {
        return scallingFactor;
    }

    public void setScallingFactor(String scallingFactor) {
        this.scallingFactor = scallingFactor;
    }

    public String getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(String attenuation) {
        this.attenuation = attenuation;
    }

    public String getCheckCalculator() {
        return checkCalculator;
    }

    public void setCheckCalculator(String checkCalculator) {
        this.checkCalculator = checkCalculator;
    }
}
