package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MotorcycleApp extends JFrame implements ActionListener {

    private static JFrame addMotorcycleFrame, addServiceFrame, addPartFrame, viewMotorcycles;
    private JMenu addMotorcycle;
    private JMenu viewMotorcycle;
    private JMenu addService;
    private JMenu addPart;

    ArrayList<Motorcycle> motorcycles = new ArrayList<>();

    public MotorcycleApp(){

        createAddMenu();
        createViewMenu();
        createAddServiceMenu();
        createAddPartMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.lightGray);
        menuBar.add(addMotorcycle);
        menuBar.add(viewMotorcycle);
        menuBar.add(addService);
        menuBar.add(addPart);

        setTitle("My Motorcycle Manager");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //https://stackoverflow.com/questions/15657569/how-to-set-icon-to-jframe found image setting code here
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(getClass().getResource("javaprojectlogo.png")));

        this.add(imgLabel);
        setLayout(new FlowLayout());

        open();
    }

    public void actionPerformed(ActionEvent event){
        String menuName;
        menuName = event.getActionCommand();

        if(event.getSource().equals(addMotorcycleFrame)){
            addMotorcycleFrame.dispose();
        }

        if(menuName.equals("Add Motorcycle")){
            addMotorcycleFrame = new JFrame("Add Motorcycle");
            AddWithoutVinForm gui = new AddWithoutVinForm();
            addMotorcycleFrame.add(gui.getPanel1());
            addMotorcycleFrame.setLocationRelativeTo(null);
            addMotorcycleFrame.setSize(450, 500);
            addMotorcycleFrame.setVisible(true);
        }

        if(menuName.equals("Add Service")){
            addServiceFrame = new JFrame("Add Service");
            AddServiceForm serviceGui = new AddServiceForm();
            addServiceFrame.add(serviceGui.getPanel1());
            addServiceFrame.setLocationRelativeTo(null);
            addServiceFrame.setSize(450,500);
            addServiceFrame.setVisible(true);
        }

        if(menuName.equals("Add Part")){
            addPartFrame = new JFrame("Add Service");
            AddPartForm partGui = new AddPartForm();
            addPartFrame.add(partGui.getPanel1());
            addPartFrame.setLocationRelativeTo(null);
            addPartFrame.setSize(450,500);
            addPartFrame.setVisible(true);
        }

        if(menuName.equals("Add Without VIN")){
            addMotorcycleFrame.setVisible(true);
            System.out.println("Test");
        }

        if(menuName.equals("View List")){

            viewMotorcycles = new JFrame("View");
            viewMotorcycles viewGui = new viewMotorcycles();
            viewMotorcycles.add(viewGui.getPanel1());
            viewMotorcycles.setLocationRelativeTo(null);
            viewMotorcycles.setSize(650,400);
            viewMotorcycles.setVisible(true);
        }

        if(menuName.equals("Quit")){
            System.exit(0);
        }

    }

    private void createAddMenu() {

        JMenuItem item;

        addMotorcycle = new JMenu("Add Motorcycles");

        item = new JMenuItem("Add Motorcycle");
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

    }

    private void createAddPartMenu() {
        JMenuItem item;

        addPart = new JMenu("Add Part");

        item = new JMenuItem("Add Part");
        item.addActionListener(this);
        addPart.add(item);
    }


    public static void main(String[] args) {
        MotorcycleApp frame = new MotorcycleApp();
        frame.setVisible(true);
    }

    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("motorcycles.dat"));
        os.writeObject(motorcycles);
        os.close();
    }

    /*This is where I found .dispose() https://stackoverflow.com/questions/13360430/jframe-dispose-vs-system-exit#:~:text=dispose()%3B%20causes%20the%20JFrame,effect%20rather%20than%20the%20norm.
    * needed alternative to System.exit(0) as it closes all open JFrames and setVisible(false) wouldn't reset input fields*/
    public void closeFrame() throws IOException {
        addMotorcycleFrame.dispose();
    }

    public void closeServiceFrame() throws IOException {
        addServiceFrame.dispose();
    }

    public void closePartFrame() throws IOException {
        addPartFrame.dispose();
    }

    public void closeViewFrame() throws IOException {
        viewMotorcycles.dispose();
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
            JOptionPane.showMessageDialog(null,"Class of object deserialized not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public GregorianCalendar getDateString(String serviceDateAsString) {
        int serviceYear = 2000, serviceMonth = 1, serviceDay = 1;

        String yearSub, monthSub, daySub;
        yearSub = serviceDateAsString.substring(0,4);
        monthSub = serviceDateAsString.substring(5,7);
        daySub = serviceDateAsString.substring(8,10);

        serviceYear = Integer.parseInt(serviceDateAsString.substring(0,4));
        serviceMonth = Integer.parseInt(serviceDateAsString.substring(5,7));
        serviceDay = Integer.parseInt(serviceDateAsString.substring(8,10));

        for(int i = 0; i < yearSub.length(); i++){
            if(!Character.isDigit(yearSub.charAt(i))){
                JOptionPane.showMessageDialog(null, "Year is not valid, setting to default of 2000");
                serviceYear = 2000;
                i = yearSub.length();
            }
        }

        for(int i = 0; i < monthSub.length(); i++){
            if(!Character.isDigit(yearSub.charAt(i))){
                JOptionPane.showMessageDialog(null, "Month is not valid, setting to default of 1");
                serviceMonth = 1;
                i = monthSub.length();
            }
        }

        for(int i = 0; i < daySub.length(); i++){
            if(!Character.isDigit(daySub.charAt(i))){
                JOptionPane.showMessageDialog(null, "Day is not valid, setting to default of 1");
                serviceDay = 1;
                i = daySub.length();
            }
        }

        if(serviceDateAsString.length() != 10){
            JOptionPane.showMessageDialog(null, "Invalid Date Length, setting to default", "Invalid Length", JOptionPane.WARNING_MESSAGE);
        }

        return new GregorianCalendar(serviceYear, serviceMonth, serviceDay);
    }

}
