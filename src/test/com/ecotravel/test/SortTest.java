package com.ecotravel.test;

import com.ecotravel.main.Route;
import com.ecotravel.main.Sort;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class SortTest {
    Sort sort;
    Route route1=mock(Route.class);
    Route route2=mock(Route.class);
    Route route3=mock(Route.class);;
    ArrayList<Route> routelist1;

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
        //route1.time=1;
        //route1.routeEmissions=3.0;
        //route2=new FakeRoute("","");
        //route2.time=3;
        //route2.routeEmissions=2.0;
        //route3=new FakeRoute("","");
        //route3.time=2;
        //route3.routeEmissions=1.0;
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
        Route sorted=(Route)result.get(0);
        assertEquals(1, sorted.getTime());
        sorted=(Route)result.get(1);
        assertEquals(2, sorted.getTime());
        sorted=(Route)result.get(2);
        assertEquals(3, sorted.getTime());
    }

    @Test
    public void testSortByEmission() {
        ArrayList<Route> result=sort.sortByEmission();
        Route sorted=(Route)result.get(0);
        assertEquals(1.0, sorted.getRouteEmissions(), .01);
        sorted=(Route)result.get(1);
        assertEquals(2.0, sorted.getRouteEmissions(), .01);
        sorted=(Route)result.get(2);
        assertEquals(3.0, sorted.getRouteEmissions(), .01);

    }
}