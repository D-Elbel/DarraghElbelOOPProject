package Project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class viewMotorcycles extends MotorcycleApp {
    private JComboBox motorcycleDropdown;
    private JButton viewSubmitButton;
    private JPanel panel1;
    private JTextArea motorcycleTextArea;
    private JTextArea partsTextArea;
    private JTextArea serviceHistoryTextArea;
    private int selected;

    public viewMotorcycles() {

        $$$setupUI$$$();
        motorcycleDropdown.addActionListener(this);

        JTextArea output = new JTextArea();

        output.setText("Motorcycle Details:\n\n");

        if (viewMotorcycles.super.motorcycles.size() < 1) {
            JOptionPane.showMessageDialog(null, "No motorcycles have been added to the system", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            for (Motorcycle motorcycle : viewMotorcycles.super.motorcycles) {
                motorcycleDropdown.addItem(motorcycle.getModelName() + "\n");
            }

            selected = motorcycleDropdown.getSelectedIndex();
        }
        motorcycleDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                motorcycleTextArea.setText("");
                serviceHistoryTextArea.setText("");
                partsTextArea.setText("");

                selected = motorcycleDropdown.getSelectedIndex();

                motorcycleTextArea.setText("TestTest");
                motorcycleTextArea.append("Manufacturer: " + viewMotorcycles.super.motorcycles.get(selected).getManufacturer());
                motorcycleTextArea.append("\n\nModel Name: " + viewMotorcycles.super.motorcycles.get(selected).getModelName());
                motorcycleTextArea.append("\n\nYear: " + viewMotorcycles.super.motorcycles.get(selected).getYearCode());
                motorcycleTextArea.append("\n\nCountry of Origin: " + viewMotorcycles.super.motorcycles.get(selected).getCountryOfOrigin());
                motorcycleTextArea.append("\n\nEngine Size: " + viewMotorcycles.super.motorcycles.get(selected).getEngineDisplacement());


                String serviceOutput = "", partsOutput = "";

                for (int i = 0; i < viewMotorcycles.super.motorcycles.get(selected).getServiceHistory().length; i++) {
                    if (viewMotorcycles.super.motorcycles.get(selected).getServiceHistory()[i] != null) {
                        serviceOutput += viewMotorcycles.super.motorcycles.get(selected).getServiceHistory()[i];
                    } else {
                        i = viewMotorcycles.super.motorcycles.get(selected).getServiceHistory().length;
                    }
                }


                for (int i = 0; i < viewMotorcycles.super.motorcycles.get(selected).getPartList().length; i++) {
                    if (viewMotorcycles.super.motorcycles.get(selected).getPartList()[i] != null) {
                        partsOutput += viewMotorcycles.super.motorcycles.get(selected).getPartList()[i];
                    } else {
                        i = viewMotorcycles.super.motorcycles.get(selected).getPartList().length;
                    }
                }

                serviceHistoryTextArea.append(serviceOutput);

                partsTextArea.append(partsOutput);
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
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
        panel1.setMinimumSize(new Dimension(650, 350));
        panel1.setPreferredSize(new Dimension(650, 350));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1));
        panel2.setMinimumSize(new Dimension(650, 32));
        panel2.setPreferredSize(new Dimension(316, 50));
        panel2.putClientProperty("html.disable", Boolean.FALSE);
        panel1.add(panel2, BorderLayout.NORTH);
        motorcycleDropdown = new JComboBox();
        panel2.add(motorcycleDropdown);
        viewSubmitButton = new JButton();
        viewSubmitButton.setText("Submit");
        panel2.add(viewSubmitButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setMinimumSize(new Dimension(650, 350));
        panel3.setPreferredSize(new Dimension(500, 300));
        panel1.add(panel3, BorderLayout.CENTER);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(200, 300));
        scrollPane1.setPreferredSize(new Dimension(200, 300));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(scrollPane1, gbc);
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        serviceHistoryTextArea = new JTextArea();
        serviceHistoryTextArea.setMinimumSize(new Dimension(100, 300));
        serviceHistoryTextArea.setPreferredSize(new Dimension(150, 300));
        scrollPane1.setViewportView(serviceHistoryTextArea);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(200, 300));
        scrollPane2.setPreferredSize(new Dimension(200, 300));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(scrollPane2, gbc);
        scrollPane2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        partsTextArea = new JTextArea();
        partsTextArea.setMinimumSize(new Dimension(100, 300));
        partsTextArea.setPreferredSize(new Dimension(150, 300));
        scrollPane2.setViewportView(partsTextArea);
        final JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setMinimumSize(new Dimension(200, 350));
        scrollPane3.setPreferredSize(new Dimension(200, 300));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(scrollPane3, gbc);
        scrollPane3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        motorcycleTextArea = new JTextArea();
        motorcycleTextArea.setEditable(false);
        motorcycleTextArea.setMinimumSize(new Dimension(100, 350));
        motorcycleTextArea.setPreferredSize(new Dimension(150, 300));
        scrollPane3.setViewportView(motorcycleTextArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
