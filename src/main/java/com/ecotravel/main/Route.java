package com.ecotravel.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import com.google.gson.Gson;

public class Route {
    private int distance;
    private int time;
    private String directionsList;
    private int routeEmissions;
    //Replace this string variable with the absolute path to the file your api key is in
    final static String api_key_path = "D:/Coding/NetBeansProjects/EcoTravel API Key/api-key.txt";
    private String apiKey = "";
    String origin;
    String destination;
    private String url;
    private String urlBase = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    HttpURLConnection con;
    
    //The Route object
    //apiKey - The apiKey associated with the Google Directions API
    //origin - The address the user is starting at
    //destination - The address the user wants to travel to
    //url - the request url that consists of the origin, destination, and api key
    public Route(String origin, String destination, int emissionsPerMile) throws MalformedURLException, ProtocolException, IOException {
        this.apiKey = getApiKey();
        this.origin = origin;
        this.destination = destination;
        this.url = urlBase + this.origin + "&destination=" + this.destination + "&key=" + this.apiKey;
        establishConnection();
        //Set the distance, time, directionsList, and routeEmissions values
        parseRouteInfo(emissionsPerMile);
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
    
    //Start the connection to the API with the url
    private void establishConnection() throws MalformedURLException, ProtocolException, IOException {
        URL myurl = new URL(this.url);
        this.con = (HttpURLConnection) myurl.openConnection();
    }
    
    //Print the results of the Routes request to the API
    public void printResults() throws IOException {
        this.con.setRequestMethod("GET");
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(this.con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
    
    //Print the results of the Routes request to a file
    public void printResultsToFile() throws IOException {
        this.con.setRequestMethod("GET");
        File f = new File("routeResults.json");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(this.con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                writer.write(line);
            }
            writer.close();
        }
    }
    
    //Disconnect the connection
    public void disconnect() {
        this.con.disconnect();
    }
    
    //Set the distance, time, 
    private void parseRouteInfo(int emissionsPerMile) {
        
        //Parse the JSON file with GSON
        
        //Set the emissions per mile
        this.routeEmissions = emissionsPerMile * this.distance;
    }
    
    //Return the route's distance
    public int getDistance() {
        return this.distance;
    }
    
    //Return the route's time
    public int getTime() {
        return this.time;
    }
    
    //Return the list of directions
    public String getDirectionsList() {
        return this.directionsList;
    }
    
    //Return the route's emissions
    public int getRouteEmissions() {
        return this.routeEmissions;
    }
    
    //Calculate the route's emissions
    public int calculateRouteEmissions(int emissionsPerMile) {
        int dist = this.getDistance();
        //Do the calculation
        this.routeEmissions = emissionsPerMile * dist;
        return this.routeEmissions;
    }
}