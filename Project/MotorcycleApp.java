package Project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class MotorcycleApp extends JFrame implements ActionListener {

    private static JFrame addMotorcycleFrame, frame, addServiceFrame;
    private JMenu addMotorcycle;
    private JMenu viewMotorcycle;
    private JMenu addService;
    private JLabel response;
    private JTextField vin, manufacturer, engineSize, year, engineType, country, odometer, model,
    addVin, addManufacturer, addEngineSize, addYear, addEngineType, addCountry, addOdometer;
    private JPanel testPanel, leftPanel, addWithoutVinPanel;


    TitledBorder titledBorder;
    ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private Motorcycle motorcycle;

    public MotorcycleApp(){


        createAddMenu();
        createViewMenu();
        createAddServiceMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.lightGray);
        menuBar.add(addMotorcycle);
        menuBar.add(viewMotorcycle);
        menuBar.add(addService);

        response = new JLabel("Motorcycle Details: " );
        //add(response);

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

        addWithoutVinPanel = new JPanel();

        addVin = new JTextField();
        addVin.setEditable(true);
        addWithoutVinPanel.add(addVin);
        //addWithoutVinPanel.setLayout(new BoxLayout(testPanel,BoxLayout.X_AXIS));

        testPanel.add(vin);
        testPanel.add(manufacturer);
        testPanel.add(engineSize);
        testPanel.add(engineType);
        testPanel.add(country);
        testPanel.add(odometer);
        testPanel.add(model);
        testPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //testPanel.setVisible(true);
        add(testPanel);
        add(addWithoutVinPanel);



       // Container cPane = getContentPane();
        setLayout(new BorderLayout(2,2));
        //this.setVisible(true);
        testPanel.setVisible(false);
        addWithoutVinPanel.setVisible(false);

        open();

    }

    public void actionPerformed(ActionEvent event){
        String menuName, display;
        menuName = event.getActionCommand();

        System.out.print(menuName);

        System.out.print(event.getSource().toString());

        if(event.getSource().equals(addMotorcycleFrame)){
            addMotorcycleFrame.dispose();
        }

        if(menuName.equals("Add Via VIN")){

            addMotorcycleFrame = new JFrame("Add Motorcycle");
            AddWithoutVinForm gui = new AddWithoutVinForm();
            addMotorcycleFrame.add(gui.getPanel1());
            addMotorcycleFrame.setLocationRelativeTo(null);
            addMotorcycleFrame.setSize(400, 400);
            addMotorcycleFrame.setVisible(false);


            addMotorcycleFrame.setVisible(true);

            System.out.println("Test");
        }

        if(menuName.equals("Add Service")){
            System.out.print("\n\n\n\ntest\n\n\n");

            addServiceFrame = new JFrame("Add Service");
            AddServiceForm serviceGui = new AddServiceForm();
            addServiceFrame.add(serviceGui.getPanel1());
            addServiceFrame.setLocationRelativeTo(null);
            addServiceFrame.setSize(450,500);
            addServiceFrame.setVisible(true);

        }

        if(menuName.equals("Add Without VIN")){

                addMotorcycleFrame.setVisible(true);

            System.out.println("Test");
        }

        if(menuName.equals("View List")){

            open();
            //open();//display = motorcycles;
            displayMotorcycles();
            //response.setText(motorcycles.toString());
        }

        if(menuName.equals("Quit")){
            System.exit(0);
        }

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

    private void createAddServiceMenu() {
        JMenuItem item;

        addService = new JMenu("Add Service");

        item = new JMenuItem("Add Service");
        item.addActionListener(this);
        addService.add(item);

      //  item = new JMenuItem("Add Without VIN");
        //item.addActionListener(this);
        //addService.add(item);
    }


    public static void main(String[] args) {

        MotorcycleApp frame = new MotorcycleApp();

        frame.setVisible(true);

    /*    addMotorcycleFrame = new JFrame("Add Motorcycle");
        AddWithoutVinForm gui = new AddWithoutVinForm();
        addMotorcycleFrame.add(gui.getPanel1());
        addMotorcycleFrame.setLocationRelativeTo(null);
        addMotorcycleFrame.setSize(400, 400);
        addMotorcycleFrame.setVisible(false);


        addServiceFrame = new JFrame("Add Service");
        AddServiceForm serviceGui = new AddServiceForm();
        addServiceFrame.add(serviceGui.getPanel1());
        addServiceFrame.setLocationRelativeTo(null);
        addMotorcycleFrame.setVisible(false); */

    }

    public void save() throws IOException {

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("motorcycles.dat"));
        os.writeObject(motorcycles);
        os.close();
    }

    public void closeFrame() throws IOException {
        addMotorcycleFrame.dispose();
    }

    public void closeServiceFrame() throws IOException {
        addServiceFrame.dispose();
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
                motorcycleCombo.addItem(iterator.next().getModelName() + "\n");
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


    public void addMotorcycleWithoutVin() throws IOException {

        //Adding a motorcycle through JOption Panes, need to work on error messages associated with not inputted fields.
        Motorcycle mToAdd = new Motorcycle();

        Part partArray[] = new Part[100];
        Service serviceArray[] = new Service[100];

        mToAdd.setServiceHistory(serviceArray);
        mToAdd.setPartList(partArray);

        String modelname, manufacturer, countryOfOrigin;
        int engineDisplacement, yearCode, odometer;

        modelname = JOptionPane.showInputDialog(null, "Please enter the model name of your motorcycle: ", "Model Name",
                JOptionPane.QUESTION_MESSAGE);

        manufacturer = JOptionPane.showInputDialog(null, "Please enter the manufacturer of the motorcycle: ", "Manufacturer Name",
                JOptionPane.QUESTION_MESSAGE);

        countryOfOrigin = JOptionPane.showInputDialog(null, "Please enter the motorcycle's country of origin: ", "Country",
                JOptionPane.QUESTION_MESSAGE);

        engineDisplacement = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the motorcycle's engine displacement: ", "Engine Size",
                JOptionPane.QUESTION_MESSAGE));

        yearCode = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the year of registration ", "Year",
                JOptionPane.QUESTION_MESSAGE));

        odometer = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the motorcycle's current odometer reading ", "Odometer",
                JOptionPane.QUESTION_MESSAGE));

        mToAdd.setModelName(modelname);
        mToAdd.setManufacturer(manufacturer);
        mToAdd.setCountryOfOrigin(countryOfOrigin);
        mToAdd.setEngineDisplacement(engineDisplacement);
        mToAdd.setYearCode(yearCode);
        mToAdd.setOdometer(odometer);

        mToAdd.setVin("00000000000000000");

        motorcycles.add(mToAdd);

        addWithoutVinPanel.setVisible(true);

        save();

    }

    public GregorianCalendar getDateString(String serviceDateAsString) {
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
