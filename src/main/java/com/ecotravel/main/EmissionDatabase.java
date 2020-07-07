package com.ecotravel.main;

import java.util.ArrayList;
import java.util.List;

public class EmissionDatabase {
    private static EmissionDatabase instance=new EmissionDatabase();
    private List<EmissionData> edata;

    private EmissionDatabase() {
        edata=new ArrayList<>();
    }

    public static EmissionDatabase getInstance() {
        return instance;
    }

    public void initialize() {

    }

    public EmissionData getEmission(String type, String model) {
        EmissionData em=null;
        for (EmissionData i: edata) {
            if (i.getVeichleType().equals(type) && i.getVeichleModel().equals(model))
                em=i;
        }
        return em;
    }
}
