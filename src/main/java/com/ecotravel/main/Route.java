package com.ecotravel.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import com.google.gson.Gson;

public class Route {
    //Replace this string variable with the absolute path to the file your api key is in
    final static String api_key_path = "D:/Homework/Senior Year/Summer/SWE/api-key.txt";
    private String apiKey = "";
    String origin;
    String destination;
    private String url;
    private String urlBase = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    
    //The Route object
    //apiKey - The apiKey associated with the Google Directions API
    //origin - The address the user is starting at
    //destination - The address the user wants to travel to
    //url - the request url that consists of the origin, destination, and api key
    public Route(String origin, String destination) {
        this.apiKey = getApiKey();
        this.origin = origin;
        this.destination = destination;
        this.url = urlBase + this.origin + "&destination=" + this.destination + "&key=" + this.apiKey;
    }
    
    //Get the api key located at a local absolute path api_key_path
    private String getApiKey() {
        String key = "";
        try {
          File api_file = new File(api_key_path);
          Scanner myReader = new Scanner(api_file);
          key = myReader.nextLine();
          myReader.close();
          return key;
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        System.out.println("There was a problem getting the API key");
        return key;
    }
    
    public static void main(String [] args) throws IOException {
        String origin = "3990+Fifth+Ave+Pittsburgh+PA"; //Tower A
        String destination = "4200+Fifth+Ave+Pittsburgh+PA"; //Cathedral of Learning
        //Create a route object using an origin and destination address
        Route route = new Route(origin, destination);
        URL myurl = new URL(route.url);
        //http connection
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
        //The request is a get request
        con.setRequestMethod("GET");
        //Make sure there is an API key at the api_key_path
        if (route.apiKey.equals("")) {
            System.exit(1);
        }
        //Do the get request on route.url
        //Gson gson = new Gson(); This is a library to help with JSON structured data, not using yet
        //Read the response from the get request
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
        //Disconnect
        con.disconnect();
    }
}