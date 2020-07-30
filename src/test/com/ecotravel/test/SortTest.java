package com.ecotravel.test;

import com.ecotravel.main.Route;
import com.ecotravel.main.Sort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SortTest {
    Sort sort;
    Route route1=mock(Route.class);
    Route route2=mock(Route.class);
    Route route3=mock(Route.class);
    Route route4=mock(Route.class);
    ArrayList<Route> routelist1;

    @Before
    public void setUp() {
        when(route1.getTime()).thenReturn(5);
        when(route1.getRouteEmissions()).thenReturn(3.0);
        when(route2.getTime()).thenReturn(3);
        when(route2.getRouteEmissions()).thenReturn(2.0);
        when(route3.getTime()).thenReturn(5);
        when(route3.getRouteEmissions()).thenReturn(1.0);
        when(route4.getTime()).thenReturn(3);
        when(route4.getRouteEmissions()).thenReturn(5.0);
        routelist1=new ArrayList<>();
        routelist1.add(route1);
        routelist1.add(route2);
        routelist1.add(route3);
        routelist1.add(route4);
        sort=new Sort(routelist1);
    }

    @After
    public void tearDown() {
        routelist1=null;
    }

    @Test
    public void testSortByTime() {
        //for (Route r: routelist1)
            //System.out.println(r.getTime());
        ArrayList<Route> result=sort.sortByTime();
        Route sorted=result.get(0);
        assertEquals(3, sorted.getTime());
        sorted=(Route)result.get(1);
        assertEquals(3, sorted.getTime());
        sorted=(Route)result.get(2);
        assertEquals(5, sorted.getTime());
        sorted=(Route)result.get(3);
        assertEquals(5, sorted.getTime());
    }

    @Test
    public void testSortByEmission() {
        //for (Route r: routelist1)
            //System.out.println(r.getRouteEmissions());
        ArrayList<Route> result=sort.sortByEmission();
        Route sorted=result.get(0);
        assertEquals(1.0, sorted.getRouteEmissions(), .01);
        sorted=result.get(1);
        assertEquals(2.0, sorted.getRouteEmissions(), .01);
        sorted=result.get(2);
        assertEquals(3.0, sorted.getRouteEmissions(), .01);
        sorted=result.get(3);
        assertEquals(5.0, sorted.getRouteEmissions(), .01);

    }
}