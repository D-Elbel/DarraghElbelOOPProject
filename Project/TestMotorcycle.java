package Project;

import javax.swing.*;

public class TestMotorcycle {

    public static void main(String[] args) {

        String testVIN;

        testVIN = JOptionPane.showInputDialog(null, "Please enter VIN", "Input", JOptionPane.QUESTION_MESSAGE);

        Motorcycle m1 = new Motorcycle(testVIN);



        //System.out.print(m1.validateVIN(testVIN));

        System.out.println(m1.toString());

        System.exit(0);
    }
}
