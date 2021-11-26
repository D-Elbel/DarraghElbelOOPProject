package Project;

import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TestMotorcycle {

    public static void main(String[] args) {

        String testVIN;
        boolean isvalid;

        Part partArray[] = new Part[100];
        Service serviceArray[] = new Service[100];

        partArray[0] = new Part("Gear Position Indicator", "Aftermarket Magnetic Gear Position Indicator", new GregorianCalendar(2021, 11,11));

        ArrayList<Motorcycle> myMotorcycles = new ArrayList<Motorcycle>();

        testVIN = JOptionPane.showInputDialog(null, "Please enter VIN", "Input", JOptionPane.QUESTION_MESSAGE);

        isvalid = validateVIN(testVIN);

        partArray[0] = addPartDriver();
        serviceArray[0] = addServiceDriver();

        if(isvalid){
            Motorcycle m1 = new Motorcycle(testVIN, partArray);
            m1.setPartList(partArray);
            m1.setServiceHistory(serviceArray);
            System.out.println(m1.toString());

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
        GregorianCalendar installDate = null;

        name = JOptionPane.showInputDialog(null, "Please enter part name", "Part Name", JOptionPane.QUESTION_MESSAGE);
        description = JOptionPane.showInputDialog(null, "Please enter part description", "Description", JOptionPane.QUESTION_MESSAGE);

        installDateAsString = JOptionPane.showInputDialog(null, "Please enter the date of install in the format 'Year-Month-Day", "Install Date", JOptionPane.QUESTION_MESSAGE);
            
        //Validating and setting part install date
        installDate = getDateString(installDateAsString);

        Part partAdd = new Part();

        partAdd.setName(name);
        partAdd.setDescription(description);
        partAdd.setInstallDate(installDate);

        return partAdd;
    }

    public static Service addServiceDriver(){

        String serviceNotes, dealerOrSelf,serviceDateAsString;
        int serviceDay = 1, serviceMonth = 1, serviceYear = 1;
        GregorianCalendar installDate = null;

        serviceNotes = JOptionPane.showInputDialog(null, "Please enter any service notes you have", "Part Name", JOptionPane.QUESTION_MESSAGE);


        serviceDateAsString = JOptionPane.showInputDialog(null, "Please enter the date of service in the format 'Year-Month-Day", "Service Date", JOptionPane.QUESTION_MESSAGE);

        //Taking in and validating service type, toLowerCase ensures entry does not have to be case sensitive
        dealerOrSelf = JOptionPane.showInputDialog(null, "Was this a dealer/garage service? Please answer 'Yes' or 'No'", "Service Type", JOptionPane.QUESTION_MESSAGE);
        dealerOrSelf = dealerOrSelf.toLowerCase();

        if(!dealerOrSelf.equals("yes") && !dealerOrSelf.equals("no")){

            while(!dealerOrSelf.equals("yes") && !dealerOrSelf.equals("no")){
                dealerOrSelf = JOptionPane.showInputDialog(null, "Invalid Entry, please try again", "Invalid Entry", JOptionPane.QUESTION_MESSAGE);
                dealerOrSelf = dealerOrSelf.toLowerCase();
            }
        }

        if(dealerOrSelf.equals("yes")){
            dealerOrSelf = "Dealer/Garage Service";
        }
        else{
            dealerOrSelf = "Self Service";
        }

        //Validating and setting part install date
        installDate = getDateString(serviceDateAsString);
        //GregorianCalendar installDate;

        Service newService = new Service();

        newService.setServiceDate(installDate);
        newService.setServiceNotes(serviceNotes);
        newService.setDealerOrSelf(dealerOrSelf);

        return newService;
    }

    private static GregorianCalendar getDateString(String serviceDateAsString) {
        int serviceYear;
        int serviceMonth;
        int serviceDay;
        if(!Character.isDigit(serviceDateAsString.substring(0,4).charAt(0)) || !Character.isDigit(serviceDateAsString.substring(0,4).charAt(1)) || !Character.isDigit(serviceDateAsString.substring(0,4).charAt(2)) || !Character.isDigit(serviceDateAsString.substring(0,4).charAt(3))){
            JOptionPane.showMessageDialog(null, "Year is not valid, setting to default of 2000");
            serviceYear = 2000;
        }
        else{
            serviceYear = Integer.parseInt(serviceDateAsString.substring(0,4));
        }

        if(!Character.isDigit(serviceDateAsString.substring(5,7).charAt(0)) || !Character.isDigit(serviceDateAsString.substring(5,7).charAt(1))){
            JOptionPane.showMessageDialog(null, "Month is not valid, setting to default of 1");
            serviceMonth = 1;
        }
        else{
            serviceMonth = Integer.parseInt(serviceDateAsString.substring(5,7));
        }

        if(!Character.isDigit(serviceDateAsString.substring(8,10).charAt(0)) || !Character.isDigit(serviceDateAsString.substring(8,10).charAt(1))){
            JOptionPane.showMessageDialog(null, "Day is not valid, setting to default of 1");
            serviceDay = 1;
        }
        else{
            serviceDay = Integer.parseInt(serviceDateAsString.substring(8,10));
        }

        GregorianCalendar installDate = new GregorianCalendar(serviceYear, serviceMonth, serviceDay);

        return installDate;
    }
}
