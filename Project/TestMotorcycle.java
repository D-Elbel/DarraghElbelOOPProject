package Project;

import javax.swing.*;

public class TestMotorcycle {

    public static void main(String[] args) {

        String testVIN;
        boolean isvalid;

        testVIN = JOptionPane.showInputDialog(null, "Please enter VIN", "Input", JOptionPane.QUESTION_MESSAGE);

        isvalid = validateVIN(testVIN);

        if(isvalid){
            Motorcycle m1 = new Motorcycle(testVIN);
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
}
