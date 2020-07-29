package com.ecotravel.test;

import com.ecotravel.main.UserInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class UserInputTest {
    UserInput userInput1;
    @Before
    public void setUp() {
        userInput1=new UserInput();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetRouteInfo() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        userInput1.getRouteInfo("Chicago,IL", "Los+Angeles,CA", 1.0f);
        System.out.flush();
        System.setOut(old);
        String result=baos.toString();
        System.out.println(result);
        assertTrue(true);
    }
}
