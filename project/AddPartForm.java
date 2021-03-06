package project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddPartForm extends MotorcycleApp {

    private JPanel panel1;
    private JTextField serviceDateTextBox;
    private JTextPane partDescriptionText;
    private JComboBox motorcycleDropdown;
    private JButton partSubmitButton;
    private JLabel partNameLabel;
    private JTextField partNameText;
    private JLabel partInstallDate;
    private int selected;

    public AddPartForm() {

        $$$setupUI$$$();
        motorcycleDropdown.addActionListener(this);

        JTextArea output = new JTextArea();

        output.setText("Motorcycle Details:\n\n");

        if (AddPartForm.super.motorcycles.size() < 1) {
            JOptionPane.showMessageDialog(null, "No motorcycles have been added to the system", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            for (Motorcycle motorcycle : AddPartForm.super.motorcycles) {
                motorcycleDropdown.addItem(motorcycle.getModelName() + "\n");
            }

            selected = motorcycleDropdown.getSelectedIndex();
        }

        partSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Part partToAdd = new Part();

                selected = motorcycleDropdown.getSelectedIndex();

                partToAdd.setName(partNameText.getText());
                partToAdd.setDescription(partDescriptionText.getText());
                partToAdd.setInstallDate(AddPartForm.super.getDateString(serviceDateTextBox.getText()));

                for (int i = 0; i < AddPartForm.super.motorcycles.get(selected).getPartList().length; i++) {

                    if (AddPartForm.super.motorcycles.get(selected).getPartList()[i] == null) {
                        AddPartForm.super.motorcycles.get(selected).getPartList()[i] = partToAdd;

                        i = AddPartForm.super.motorcycles.get(selected).getPartList().length;
                    }
                }

                try {
                    AddPartForm.super.save();
                    AddPartForm.super.closePartFrame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }


    public JPanel getPanel1() {
        return panel1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        panel1.setPreferredSize(new Dimension(350, 415));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1));
        panel2.setMinimumSize(new Dimension(300, 32));
        panel2.setPreferredSize(new Dimension(316, 50));
        panel2.putClientProperty("html.disable", Boolean.FALSE);
        panel1.add(panel2, BorderLayout.NORTH);
        motorcycleDropdown = new JComboBox();
        panel2.add(motorcycleDropdown);
        partNameLabel = new JLabel();
        partNameLabel.setText("Part Name");
        panel2.add(partNameLabel);
        partNameText = new JTextField();
        partNameText.setPreferredSize(new Dimension(55, 30));
        panel2.add(partNameText);
        partInstallDate = new JLabel();
        partInstallDate.setText("installDate");
        panel2.add(partInstallDate);
        serviceDateTextBox = new JTextField();
        serviceDateTextBox.setPreferredSize(new Dimension(55, 30));
        panel2.add(serviceDateTextBox);
        partSubmitButton = new JButton();
        partSubmitButton.setText("Submit");
        panel2.add(partSubmitButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel3.setPreferredSize(new Dimension(19, 365));
        panel1.add(panel3, BorderLayout.CENTER);
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        partDescriptionText = new JTextPane();
        panel3.add(partDescriptionText, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setText("Enter Part Description Below");
        panel3.add(label1, BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
