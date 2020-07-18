package com.ecotravel.main;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class UserInput{
    private static String vehicleType;
    public static void main(String[] args){
            JFrame inputFrame = new JFrame("User Information");
            inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String startLocation = inputStartLocation(inputFrame); //Sample addresses: 3990 Fifth Ave Pittsburgh PA
            String destLocation = inputDestLocation(inputFrame);    //                  4200 Fifth Ave Pittsburgh PA
            String startAddress = startLocation.replace(' ', '+');  //Replace all spaces with + for the maps api
            String destAddress = destLocation.replace(' ', '+');
            int maxTime = inputMaxTime(inputFrame);
            float carMileagePerGallon = -1;
            inputVehicleType();
            if (vehicleType.equals("Gas")){
                    carMileagePerGallon = inputGasMileage(inputFrame);
            }
            //Create a route object using an origin and destination address
            Route route;
            try {
                route = new Route(startAddress, destAddress, carMileagePerGallon);
                System.out.println("Route disconnected");
            }
            catch (MalformedURLException e) {
                System.out.println("Malformed url exception occurred");
                e.printStackTrace();
            }
            catch (ProtocolException e) {
                System.out.println("Protocol Exception occurred");
                e.printStackTrace();
            }
            catch (IOException e) {
                System.out.println("IO Exception occurred");
                e.printStackTrace();
            }
            catch (ParseException e) {
                System.out.println("Parse Exception occurred");
                e.printStackTrace();
            }
            
    }
    public static String inputStartLocation(final JFrame frame){
            return (String)JOptionPane.showInputDialog(frame, "Input starting location address:", "Starting Location",
            JOptionPane.PLAIN_MESSAGE, null, null, null);
    }
    public static String inputDestLocation(final JFrame frame){
            return (String)JOptionPane.showInputDialog(frame, "Input destination location address:", "Destination Location",
            JOptionPane.PLAIN_MESSAGE, null, null, null);
    }
    public static int inputMaxTime(final JFrame frame){
            return Integer.valueOf((String)JOptionPane.showInputDialog(frame, "Input the maximum amount of time (in minutes) you have to travel:", "Max Time",
            JOptionPane.PLAIN_MESSAGE, null, null, null));
    }
    public static void inputVehicleType(){
        try {
            Object[] possibleValues = { "Gas", "Electric", "Hybrid" };
            Object selectedValue = JOptionPane.showInputDialog(null,
            "Select your personal vehicle type:", "Vehicle Type",
            JOptionPane.INFORMATION_MESSAGE, null,
            possibleValues, possibleValues[0]);
            if (selectedValue == null) {
                System.out.println("User selected cancel");
            }
            else {
                vehicleType = selectedValue.toString();
            }
        }
        catch (NullPointerException e) {
            System.out.println("User selected cancel");
            System.exit(0);
        }

    }
    public static int inputGasMileage(final JFrame frame){
            return Integer.valueOf((String)JOptionPane.showInputDialog(frame, "Input the gas mileage (in miles/gallon) of your personal vehicle:", "Gas Mileage",
            JOptionPane.PLAIN_MESSAGE, null, null, null));
    }
}

