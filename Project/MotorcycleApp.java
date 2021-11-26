package Project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setLayout(new FlowLayout());


        setVisible(true);

    }

    public void actionPerformed(ActionEvent event){
        String menuName;
        menuName = event.getActionCommand();

        System.out.print(menuName);

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

}
