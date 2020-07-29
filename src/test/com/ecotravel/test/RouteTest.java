package com.ecotravel.test;

import com.ecotravel.main.Route;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class RouteTest {
    Route route1=null;

    @Before
    public void setUp() {
        try {
            route1=new Route("Chicago,IL", "Los+Angeles,CA");
            route1.connect();
        } catch (Exception e) {
            e.printStackTrace();
            fail("failed to initialize Route class!");
        }
    }

    @After
    public void tearDown() {
        route1.disconnect();
        route1=null;
    }

    @Test
    public void testRouteConnection() {
        Route myRoute=null;
        try {
            myRoute=new Route("Chicago,IL", "Los+Angeles,CA");
            myRoute.connect();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            myRoute.disconnect();
        }
    }

    @Test
    public void testCalculateRouteEmissions() {
        double result=route1.calculateRouteEmissions(1.0f);
        System.out.println("Route emissions: " + result);
    }

    @Test
    public void testGetRouteInfo() {
        try {
            route1.getRouteInfo(1.0f);
            System.out.println(route1.getTime() + route1.getDistance());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }
}
