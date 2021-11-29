package Project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MotorcycleApp extends JFrame implements ActionListener {

    private JMenu addMotorcycle;
    private JMenu viewMotorcycle;
    private JLabel response;
    private JTextField vin, manufacturer, engineSize, year, engineType, country, odometer, model;
    private JPanel testPanel;

    TitledBorder titledBorder;
    ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private Motorcycle motorcycle;

    public MotorcycleApp(){

        createAddMenu();
        createViewMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.lightGray);
        menuBar.add(addMotorcycle);
        menuBar.add(viewMotorcycle);

        response = new JLabel("Menu Tester" );
        add(response);

        setTitle("My Motorcycle Manager");
        setSize(650, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        vin = new JTextField();
        vin.setEditable(false);

        manufacturer = new JTextField();
        manufacturer.setEditable(false);

        engineSize = new JTextField();
        engineSize.setEditable(false);

        year = new JTextField();
        year.setEditable(false);

        engineType = new JTextField();
        engineType.setEditable(false);

        country = new JTextField();
        country.setEditable(false);

        odometer = new JTextField();
        odometer.setEditable(false);

        model = new JTextField();
        model.setEditable(false);

        testPanel = new JPanel();
        testPanel.setLayout(new FlowLayout());

        testPanel.add(vin);
        testPanel.add(manufacturer);
        testPanel.add(engineSize);
        testPanel.add(engineType);
        testPanel.add(country);
        testPanel.add(odometer);
        testPanel.add(model);
        //testPanel.setVisible(true);
        add(testPanel);


       // Container cPane = getContentPane();
        setLayout(new FlowLayout());
        this.setVisible(true);
        testPanel.setVisible(false);
        open();

    }



    public void actionPerformed(ActionEvent event){
        String menuName, display;
        menuName = event.getActionCommand();

        System.out.print(menuName);

        if(menuName.equals("Add Via VIN")){
            try {
                addMotorcycleByVin();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Test");
        }

        if(menuName.equals("View List")){
            //display = motorcycles;
            displayMotorcycles();
            //response.setText(motorcycles.toString());


        }

        if(menuName.equals("Quit")){
            System.exit(0);
        }
        //else{
         //   response.setText("Menu Item " + menuName + " is selected.");
        //}



        //Combobox stuff


    }

    private void createAddMenu() {

        JMenuItem item;

        addMotorcycle = new JMenu("Add Motorcycles");

        item = new JMenuItem("Add Via VIN");
        item.addActionListener(this);
        addMotorcycle.add(item);

        item = new JMenuItem("Add Without VIN");
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

    public void save() throws IOException {

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("motorcycles.dat"));
        os.writeObject(motorcycles);
        os.close();
    }


    public void displayMotorcycles() {
        JComboBox motorcycleCombo = new JComboBox();

        motorcycleCombo.addActionListener(this);

        JTextArea output = new JTextArea();

        output.setText("Motorcycle Details:\n\n");

        if(motorcycles.size() < 1) {
            JOptionPane.showMessageDialog(null,"No motorcycles have been added to the system","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {
            Iterator<Motorcycle> iterator = motorcycles.iterator();

            while(iterator.hasNext()) {
                motorcycleCombo.addItem(iterator.next().getVin() + "\n");
            }

            JOptionPane.showMessageDialog(null,motorcycleCombo,"Select your motorcycle to view details",JOptionPane.PLAIN_MESSAGE);

            int selected = motorcycleCombo.getSelectedIndex();
            output.append(motorcycles.get(selected).toString());

            JOptionPane.showMessageDialog(null,output,"Motorcycle Details",JOptionPane.PLAIN_MESSAGE);

            testPanel.setVisible(true);
            vin.setText(motorcycles.get(selected).getVin());
            manufacturer.setText(motorcycles.get(selected).getManufacturer());
            engineSize.setText(Integer.toString(motorcycles.get(selected).getEngineDisplacement()));
            model.setText(motorcycles.get(selected).getModelName());
        }


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

    public void addMotorcycleByVin() throws IOException {

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

        save();

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
