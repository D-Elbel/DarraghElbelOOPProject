package Project;

import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TestMotorcycle {

    public static void main(String[] args) {

        String testVIN;
        boolean isvalid;

        Part partArray[] = new Part[100];

        partArray[0] = new Part("Gear Position Indicator", "Aftermarket Magnetic Gear Position Indicator", new GregorianCalendar(2021, 11,11));

        ArrayList<Motorcycle> myMotorcycles = new ArrayList<Motorcycle>();

        testVIN = JOptionPane.showInputDialog(null, "Please enter VIN", "Input", JOptionPane.QUESTION_MESSAGE);

        isvalid = validateVIN(testVIN);

        partArray[0] = addPartDriver();



        if(isvalid){
            Motorcycle m1 = new Motorcycle(testVIN, partArray);
            System.out.println(m1.toString());
            m1.setPartList(partArray);
        }

        //System.out.print(m1.validateVIN(testVIN));

        System.exit(0);
    }

    public static boolean validateVIN(String vin){

        String visSerialCheck = "";

        //Verifying length
        if(vin.length() != 17){
            JOptionPane.showMessageDialog(null, "VINs must be 17 characters in length", "Invalid Length", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Verifying valid VIS format
        visSerialCheck = vin.substring(10,17);

        for(int i = 0; i < visSerialCheck.length(); i++){
            if(Character.isDigit(visSerialCheck.charAt(i)) == false){
                JOptionPane.showMessageDialog(null, "Motorcycle VIS be a numeric value six digits in length", "Invalid VIS Format", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public static Part addPartDriver(){

        String name, description,installDateAsString;
        int installDay = 1, installMonth = 1, installYear = 1;
        //GregorianCalendar installDate = null;

        name = JOptionPane.showInputDialog(null, "Please enter part name", "Part Name", JOptionPane.QUESTION_MESSAGE);
        description = JOptionPane.showInputDialog(null, "Please enter part description", "Description", JOptionPane.QUESTION_MESSAGE);

        installDateAsString = JOptionPane.showInputDialog(null, "Please enter the date of install in the format 'Year-Month-Day", "Install Date", JOptionPane.QUESTION_MESSAGE);
            
        //Validating and setting part install date
        if(!Character.isDigit(installDateAsString.substring(0,4).charAt(0)) || !Character.isDigit(installDateAsString.substring(0,4).charAt(1)) || !Character.isDigit(installDateAsString.substring(0,4).charAt(2)) || !Character.isDigit(installDateAsString.substring(0,4).charAt(3))){
            JOptionPane.showMessageDialog(null, "Year is not valid, setting to default of 2000");
            installYear = 2000;
        }
        else{
            installYear = Integer.parseInt(installDateAsString.substring(0,4));
        }

        if(!Character.isDigit(installDateAsString.substring(5,7).charAt(0)) || !Character.isDigit(installDateAsString.substring(5,7).charAt(1))){
            JOptionPane.showMessageDialog(null, "Month is not valid, setting to default of 1");
            installMonth = 1;
        }
        else{
            installMonth = Integer.parseInt(installDateAsString.substring(5,7));
        }

        if(!Character.isDigit(installDateAsString.substring(8,10).charAt(0)) || !Character.isDigit(installDateAsString.substring(8,10).charAt(1))){
            JOptionPane.showMessageDialog(null, "Day is not valid, setting to default of 1");
            installDay = 1;
        }
        else{
            installDay = Integer.parseInt(installDateAsString.substring(8,10));
        }

        GregorianCalendar installDate = new GregorianCalendar(installYear, installMonth, installDay);

        Part partAdd = new Part();

        partAdd.setName(name);
        partAdd.setDescription(description);
        partAdd.setInstallDate(installDate);

        return partAdd;
    }
}
