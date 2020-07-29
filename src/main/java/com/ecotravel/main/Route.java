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
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class Route {
    private float distance;
    private int time;
    private ArrayList<String> directionsList;
    private double routeEmissions;
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
    public Route(String origin, String destination) throws MalformedURLException, ProtocolException, IOException, ParseException {
        this.apiKey = getApiKey();
        this.origin = origin;
        this.destination = destination;
        this.url = urlBase + this.origin + "&destination=" + this.destination + "&key=" + this.apiKey;
        this.directionsList = new ArrayList<>();
        //Set the distance, time, directionsList, and routeEmissions values
        this.con.disconnect();
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
    public void connect() throws MalformedURLException, ProtocolException, IOException {
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
    
    //Set the distance and time of the route 
    public void getRouteInfo(double emissionsPerMile) throws MalformedURLException, ProtocolException, IOException, ParseException {
        //Perform the request
        this.con.setRequestMethod("GET");
        StringBuilder strBuild = new StringBuilder();
        //Read in the results
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(this.con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                strBuild.append(line);
            }
            in.close();
        }
        catch(Exception e) {
        }
        String json = strBuild.toString();          //This is the full string of results from the query
        
        //Parse this string for distance, time, directions list etc.
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jb = (JSONObject) obj;
        
        //Read the data
        JSONArray routesArray = (JSONArray) jb.get("routes");
        JSONObject thisRoute = (JSONObject) routesArray.get(0);
        JSONArray legs = (JSONArray) thisRoute.get("legs");
        JSONObject thisLeg = (JSONObject) legs.get(0);
        JSONObject totalDistance = (JSONObject) thisLeg.get("distance");
        JSONObject totalDuration = (JSONObject) thisLeg.get("duration");
        JSONArray steps = (JSONArray) thisLeg.get("steps");
        //Set the Directions
        for (int i = 0; i < steps.size(); i++) {
            JSONObject thisStep = (JSONObject) steps.get(i);
            String htmlinstructions = thisStep.get("html_instructions").toString();
            this.directionsList.add(normalize(htmlinstructions));
        }
        //Set the route distance
        String[] dist = totalDistance.get("text").toString().split(" ");
        String[] dur = totalDuration.get("text").toString().split(" ");
        //Set the time
        this.time = Integer.parseInt(dur[0]);
        //Set the distance
        this.distance = Float.parseFloat(dist[0]);
        //Set the route emissions
        this.routeEmissions = calculateRouteEmissions(emissionsPerMile);
    }
    
    //Parse the string without the html elements
    private String normalize(String instructions) {
        String[] inst = instructions.split("<(.*?)>");
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < inst.length; i++) {
            String[] dest = inst[i].split(" ");
            if (dest[0].equals("Destination")) {
                str.append(" - ");
            }
                str.append(inst[i]);
        }
        return str.toString();
    }
    //Return the route's distance
    public float getDistance() {
        return this.distance;
    }
    
    //Return the route's time
    public int getTime() {
        return this.time;
    }
    
    //Return the list of directions
    public ArrayList<String> getDirectionsList() {
        return this.directionsList;
    }
    
    //Return the route's emissions
    public double getRouteEmissions() {
        return this.routeEmissions;
    }
    
    //Calculate the route's emissions
    public double calculateRouteEmissions(double emissionsPerMile) {
        if (emissionsPerMile == -1) {
            return -1;
        }
        DecimalFormat df2 = new DecimalFormat("#.##");
        return Double.parseDouble(df2.format(this.getDistance() / emissionsPerMile));
    }
}