package Project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MotorcycleApp extends JFrame implements ActionListener {

    private JMenu addMotorcycle;
    private JMenu viewMotorcycle;
    private JLabel response;


    TitledBorder titledBorder;
    ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private Motorcycle motorcycle;

    public MotorcycleApp(){

        createAddMenu();
        createViewMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.yellow);
        menuBar.add(addMotorcycle);
        menuBar.add(viewMotorcycle);

        response = new JLabel("Menu Tester" );
        add(response);

        setTitle("Motorcycle Management");
        setSize(650, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       // Container cPane = getContentPane();
        setLayout(new GridBagLayout());
        setVisible(true);

        open();

    }

    public void actionPerformed(ActionEvent event){
        String menuName;
        menuName = event.getActionCommand();

        System.out.print(menuName);

        if(menuName.equals("New VIN")){
            addMotorcycleByVin();
        }

        if(menuName.equals("Quit")){
            System.exit(0);
        }
        else{
            response.setText("Menu Item " + menuName + " is selected.");
        }
    }

    private void createAddMenu() {

        JMenuItem item;

        addMotorcycle = new JMenu("Add Motorcycles");
        item = new JMenuItem("New VIN");
        item.addActionListener(this);
        addMotorcycle.add(item);
    }

    private void createViewMenu() {
        JMenuItem item;

        viewMotorcycle = new JMenu("View Motorcycles");
        item = new JMenuItem("View List");
        item.addActionListener(this);

        viewMotorcycle.add(item);


    }

    public static void main(String[] args) {
        MotorcycleApp frame = new MotorcycleApp();
    }

    public void open() {
        try {

            File file = new File("motorcycles.dat");

            if(file.exists()) {

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                motorcycles = (ArrayList<Motorcycle>) is.readObject();
                is.close();

                JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialised not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public void addMotorcycleByVin(){

        String vinToAdd;
        boolean isvalid;

        Part partArray[] = new Part[100];
        Service serviceArray[] = new Service[100];

        vinToAdd = JOptionPane.showInputDialog(null, "Please enter VIN", "Input", JOptionPane.QUESTION_MESSAGE);

        isvalid = validateVIN(vinToAdd);

        //partArray[0] = addPartDriver();
        //serviceArray[0] = addServiceDriver();

        if(isvalid){
            Motorcycle m1 = new Motorcycle(vinToAdd, partArray);
            m1.setPartList(partArray);
            m1.setServiceHistory(serviceArray);
            System.out.println(m1.toString());
            motorcycles.add(m1);
        }


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
