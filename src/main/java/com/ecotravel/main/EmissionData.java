package com.ecotravel.main;

public class EmissionData {
    private String veichleType;
    private String veichleModel;
    private double fuelConsumptionRate;

    public EmissionData(String vt, String vm, double rate) {
        veichleType=vt;
        veichleModel=vm;
        fuelConsumptionRate=rate;
    }

    public String getVeichleType() {
        return veichleType;
    }

    public String getVeichleModel() {
        return veichleModel;
    }

    public double getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }
}
