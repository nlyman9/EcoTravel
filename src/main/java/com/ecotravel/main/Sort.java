package com.ecotravel.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort {

    class RouteTimeComparator implements Comparator<Route> {
        public int compare(Route a, Route b) {
            return a.getTime() > b.getTime() ? +1 : a.getTime() < b.getTime() ? -1 : 0;
        }
    }

    class RouteEmissionComparator implements Comparator<Route> {
        public int compare(Route a, Route b) {
            return a.getRouteEmissions() > b.getRouteEmissions() ? +1 : a.getRouteEmissions() < b.getRouteEmissions() ? -1 : 0;
        }
    }

    private ArrayList<Route> routesList;

    public Sort(ArrayList<Route> rl) {
        routesList=(ArrayList<Route>) rl.clone();
    }

    public ArrayList<Route> getRoutesList() {
        return routesList;
    }

    public ArrayList<Route> sortByTime() {
        Collections.sort(routesList, new RouteEmissionComparator());
        return routesList;
    }

    public ArrayList<Route> sortByEmission() {
        Collections.sort(routesList, new RouteTimeComparator());
        return routesList;
    }

    public boolean belowMaxTime(Route route) {
        return false;
    }
}
