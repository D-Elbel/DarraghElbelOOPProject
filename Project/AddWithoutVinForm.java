package Project;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class AddWithoutVinForm extends MotorcycleApp {

    private JPanel panel1;
    private JPanel panel2;
    private JButton addWithoutVinButton;
    private JTextField manufacturerString;
    private JTextField modelString;
    private JTextField engineSizeString;
    private JTextField yearString;
    private JTextField engineTypeString;
    private JTextField countryOfOriginString;
    private JLabel headerLabel;
    private JTextField vinEntry;
    private JButton addViaVINButton;

    Motorcycle bikeToAdd = new Motorcycle();

    ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private Motorcycle motorcycle;

    public AddWithoutVinForm() {

        open1();

        addWithoutVinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(null, "Motorcycle Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                Part partArray[] = new Part[100];
                Service serviceArray[] = new Service[100];

                bikeToAdd.setServiceHistory(serviceArray);
                bikeToAdd.setPartList(partArray);


                bikeToAdd.setManufacturer(manufacturerString.getText());
                bikeToAdd.setModelName(modelString.getText());
                bikeToAdd.setEngineDisplacement(Integer.parseInt(engineSizeString.getText()));
                bikeToAdd.setEngineType(engineTypeString.getText());
                bikeToAdd.setYearCode(Integer.parseInt(yearString.getText()));
                bikeToAdd.setCountryOfOrigin(countryOfOriginString.getText());

                bikeToAdd.setVin("00000000000000000");

                motorcycles.add(bikeToAdd);

                try {
                    save1();
                    headerLabel.setText("Added, please add another or close window.");
                    AddWithoutVinForm.super.closeFrame();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                manufacturerString.setText("");
                modelString.setText("");
                engineSizeString.setText("");
                yearString.setText("");
                countryOfOriginString.setText("");

                addWithoutVinButton.setActionCommand("Motorcycle Added");
            }
        });


        addViaVINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String vinToAdd;
                boolean isvalid;

                Part partArray[] = new Part[100];
                Service serviceArray[] = new Service[100];

                vinToAdd = vinEntry.getText();

                isvalid = validateVIN(vinToAdd);

                if (isvalid) {
                    Motorcycle m1 = new Motorcycle(vinToAdd, partArray);
                    m1.setPartList(partArray);
                    m1.setServiceHistory(serviceArray);
                    System.out.println(m1.toString());
                    motorcycles.add(m1);
                }

                try {
                    save();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static boolean validateVIN(String vin) {

        String visSerialCheck = "";

        //Verifying length
        if (vin.length() != 17) {
            JOptionPane.showMessageDialog(null, "VINs must be 17 characters in length", "Invalid Length", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Verifying valid VIS format
        visSerialCheck = vin.substring(10, 17);

        for (int i = 0; i < visSerialCheck.length(); i++) {
            if (Character.isDigit(visSerialCheck.charAt(i)) == false) {
                JOptionPane.showMessageDialog(null, "Motorcycle VIS be a numeric value six digits in length", "Invalid VIS Format", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }


    public void open1() {
        try {

            File file = new File("motorcycles.dat");

            if (file.exists()) {

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                motorcycles = (ArrayList<Motorcycle>) is.readObject();
                is.close();

                // JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            } else {
                file.createNewFile();
                // JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null, "Class of object deserialised not a match for anything used in this application", "Error", JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problem reading from the file", "Error", JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public void save1() throws IOException {

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("motorcycles.dat"));
        os.writeObject(motorcycles);
        os.close();
    }

    public JPanel getPanel1() {
        return panel1;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel1.add(panel2, BorderLayout.NORTH);
        headerLabel = new JLabel();
        Font headerLabelFont = this.$$$getFont$$$("Lucida Sans", Font.BOLD, 16, headerLabel.getFont());
        if (headerLabelFont != null) headerLabel.setFont(headerLabelFont);
        headerLabel.setText("Please Add Your Motorcycle Details");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 0, 0, 0);
        panel2.add(headerLabel, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer1, gbc);
        vinEntry = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(vinEntry, gbc);
        addViaVINButton = new JButton();
        addViaVINButton.setText("Add Via VIN");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(addViaVINButton, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel1.add(panel3, BorderLayout.SOUTH);
        addWithoutVinButton = new JButton();
        addWithoutVinButton.setText("Manually Add Motorcycle");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel3.add(addWithoutVinButton, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer3, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel1.add(panel4, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setText("Manufacturer");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel4.add(label1, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer4, gbc);
        manufacturerString = new JTextField();
        manufacturerString.setColumns(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel4.add(manufacturerString, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Model Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel4.add(label2, gbc);
        modelString = new JTextField();
        modelString.setColumns(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel4.add(modelString, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Engine Size");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel4.add(label3, gbc);
        engineSizeString = new JTextField();
        engineSizeString.setColumns(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel4.add(engineSizeString, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Year");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel4.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Engine Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel4.add(label5, gbc);
        yearString = new JTextField();
        yearString.setColumns(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel4.add(yearString, gbc);
        engineTypeString = new JTextField();
        engineTypeString.setColumns(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel4.add(engineTypeString, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Country Of Origin");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel4.add(label6, gbc);
        countryOfOriginString = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(countryOfOriginString, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here
        // hard-coding in some current pet data
        manufacturerString.setText("Jasper");
        modelString.setText("Joe Bloggs");
        engineSizeString.setText("Ballyporeen");
        yearString.setText("03-05-2015");
        engineTypeString.setText("087-1234567");
    }
}
