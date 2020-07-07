package com.ecotravel.test;

import com.ecotravel.main.Route;
import com.ecotravel.main.Sort;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


public class SortTest {
    Sort sort;
    private class FakeRoute extends Route {
        public int time;
        public double routeEmissions;

        public FakeRoute(String origin, String destination) {
            super(origin, destination);
        }
    }
    FakeRoute route1;
    FakeRoute route2;
    FakeRoute route3;
    ArrayList<Route> routelist1;
    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
        route1=new FakeRoute("","");
        route1.time=1;
        route1.routeEmissions=3.0;
        route2=new FakeRoute("","");
        route2.time=3;
        route2.routeEmissions=2.0;
        route3=new FakeRoute("","");
        route3.time=2;
        route3.routeEmissions=1.0;
        routelist1=new ArrayList<>();
        routelist1.add(route1);
        routelist1.add(route2);
        routelist1.add(route3);
        sort=new Sort(routelist1);
    }

    @After
    public void tearDown() {
        route1=null;
        route2=null;
        route3=null;
        routelist1=null;
    }

    @Test
    public void testSortByTime() {
        ArrayList<Route> result=sort.sortByTime();
        FakeRoute sorted=(FakeRoute)result.get(0);
        assertEquals(1, sorted.time);
        sorted=(FakeRoute)result.get(1);
        assertEquals(2, sorted.time);
        sorted=(FakeRoute)result.get(2);
        assertEquals(3, sorted.time);
    }

    @Test
    public void testSortByEmission() {
        ArrayList<Route> result=sort.sortByEmission();
        FakeRoute sorted=(FakeRoute)result.get(0);
        assertEquals(1.0, sorted.routeEmissions, .01);
        sorted=(FakeRoute)result.get(1);
        assertEquals(2.0, sorted.routeEmissions, .01);
        sorted=(FakeRoute)result.get(2);
        assertEquals(3.0, sorted.routeEmissions, .01);

    }
}