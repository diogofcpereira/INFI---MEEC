package MES_Logic;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

public class VariableDisplay extends DisplayValues {
    private final Map<String, Integer> variables;
    private final PropertyChangeSupport support;
    private final Map<String, JLabel> labels;
    private JFrame frame;

    // Constructor
    public VariableDisplay() {
        variables = new HashMap<>();
        labels = new HashMap<>();
        support = new PropertyChangeSupport(this);

        int[] initialValues = {
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0
        };

        String[] variableNames = {
                "OrderPiece", "OrderQuantity", "PiecesAlreadyProduced", "TotalProductionTime",
                "P5_Unloaded", "P6_Unloaded", "P7_Unloaded", "P9_Unloaded",
                "Cell1_MTop_totalTime", "Cell1_MBot_totalTime", "Cell2_MTop_totalTime", "Cell2_MBot_totalTime",
                "Cell3_MTop_totalTime", "Cell3_MBot_totalTime", "Cell4_MTop_totalTime", "Cell4_MBot_totalTime",
                "Cell5_MTop_totalTime", "Cell5_MBot_totalTime", "Cell6_MTop_totalTime", "Cell6_MBot_totalTime",
                "Total_Num_Cell1_Mtop", "Total_Num_Cell1_Bot", "Total_Num_Cell2_Mtop", "Total_Num_Cell2_Mbot",
                "Total_Num_Cell3_Mtop", "Total_Num_Cell3_MBot", "Total_Num_Cell4_Mtop", "Total_Num_Cell4_MBot",
                "Total_Num_Cell5_Mtop", "Total_Num_Cell5_MBot", "Total_Num_Cell6_Mtop", "Total_Num_Cell6_MBot"
        };

        for (int i = 0; i < variableNames.length; i++) {
            variables.put(variableNames[i], initialValues[i]);
        }

        // Create the GUI
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create a JFrame
        frame = new JFrame("Variable Values");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 450);

        // Create a JPanel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 50, 5, 5); // Padding between components

        // Add column titles
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        JLabel orderTitle = new JLabel("Order");
        orderTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(orderTitle, gbc);

        gbc.gridx = 2;
        JLabel machinesOperatingTimeTitle = new JLabel("Machines  Time");
        machinesOperatingTimeTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(machinesOperatingTimeTitle, gbc);

        gbc.gridx = 4;
        JLabel totalOperatedPiecesTitle = new JLabel("Total Operated Pieces");
        totalOperatedPiecesTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalOperatedPiecesTitle, gbc);

        gbc.gridx = 6;
        JLabel totalUnloaded = new JLabel("Unloaded");
        totalOperatedPiecesTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalUnloaded, gbc);

        // Add separator between title row and data rows
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 8;
        panel.add(new JSeparator(), gbc);

        gbc.gridwidth = 1; // Reset to default

        // Add labels to the panel with appropriate constraints
        String[] firstColumnNames = {"OrderPiece", "OrderQuantity", "PiecesAlreadyProduced"};
        String[] secondColumnNames = {
                "Cell1_MTop_totalTime", "Cell1_MBot_totalTime", "Cell2_MTop_totalTime", "Cell2_MBot_totalTime",
                "Cell3_MTop_totalTime", "Cell3_MBot_totalTime", "Cell4_MTop_totalTime", "Cell4_MBot_totalTime",
                "Cell5_MTop_totalTime", "Cell5_MBot_totalTime", "Cell6_MTop_totalTime", "Cell6_MBot_totalTime"
        };
        String[] thirdColumnNames = {
                "Total_Num_Cell1_Mtop", "Total_Num_Cell1_Bot", "Total_Num_Cell2_Mtop", "Total_Num_Cell2_Mbot",
                "Total_Num_Cell3_Mtop", "Total_Num_Cell3_MBot", "Total_Num_Cell4_Mtop", "Total_Num_Cell4_MBot",
                "Total_Num_Cell5_Mtop", "Total_Num_Cell5_MBot", "Total_Num_Cell6_Mtop", "Total_Num_Cell6_MBot"
        };
        String[] fourthColumnNames = { "P5_Unloaded", "P6_Unloaded", "P7_Unloaded", "P9_Unloaded"};

        int row = 2; // Start from row 2 to leave space for titles and separator
        for (String name : firstColumnNames) {
            gbc.gridx = 0;
            gbc.gridy = row;
            JLabel labelKey = new JLabel(name + ": ");
            JLabel labelValue = new JLabel(String.valueOf(variables.get(name)));
            labels.put(name, labelValue);
            gbc.insets = new Insets(5, 50, 5, 5);
            panel.add(labelKey, gbc);
            gbc.gridx = 1;
            gbc.insets = new Insets(5, 1, 5, 5);
            panel.add(labelValue, gbc);
            row++;
        }

        row = 2; // Reset row for second column
        for (String name : secondColumnNames) {
            gbc.gridx = 2;
            gbc.gridy = row;
            JLabel labelKey = new JLabel(name + ": ");
            JLabel labelValue = new JLabel(String.valueOf(variables.get(name)));
            labels.put(name, labelValue);
            gbc.insets = new Insets(5, 50, 5, 5);
            panel.add(labelKey, gbc);
            gbc.gridx = 3;
            gbc.insets = new Insets(5, 1, 5, 5);
            panel.add(labelValue, gbc);
            row++;
        }

        row = 2; // Reset row for third column
        for (String name : thirdColumnNames) {
            gbc.gridx = 4;
            gbc.gridy = row;
            JLabel labelKey = new JLabel(name + ": ");
            JLabel labelValue = new JLabel(String.valueOf(variables.get(name)));
            labels.put(name, labelValue);
            gbc.insets = new Insets(5, 50, 5, 5);
            panel.add(labelKey, gbc);
            gbc.gridx = 5;
            gbc.insets = new Insets(5, 1, 5, 5);
            panel.add(labelValue, gbc);
            row++;
        }

        row = 2; // Reset row for third column
        for (String name : fourthColumnNames) {
            gbc.gridx = 6;
            gbc.gridy = row;
            JLabel labelKey = new JLabel(name + ": ");
            JLabel labelValue = new JLabel(String.valueOf(variables.get(name)));
            labels.put(name, labelValue);
            gbc.insets = new Insets(5, 50, 5, 5);
            panel.add(labelKey, gbc);
            gbc.gridx = 7;
            gbc.insets = new Insets(5, 1, 5, 5);
            panel.add(labelValue, gbc);
            row++;
        }

        // Add vertical lines between columns
        panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));

        // Add panel to the frame
        frame.add(new JScrollPane(panel)); // Add a scroll pane for better visibility

        // Make the frame visible
        frame.setVisible(true);

        // Add a property change listener to update the labels
        support.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if (labels.containsKey(propertyName)) {
                    labels.get(propertyName).setText(String.valueOf(evt.getNewValue()));
                }
            }
        });
    }

    // Implement the displayValues method
    @Override
    public void displayValues() {
        // GUI is created in the constructor, so no additional code needed here
    }

    // Method to update values
    @Override
    public void updateValues(int[] newValues) {
        String[] variableNames = {
                "OrderPiece", "OrderQuantity", "PiecesAlreadyProduced", "TotalProductionTime",
                "P5_Unloaded", "P6_Unloaded", "P7_Unloaded", "P9_Unloaded",
                "Cell1_MTop_totalTime", "Cell1_MBot_totalTime", "Cell2_MTop_totalTime", "Cell2_MBot_totalTime",
                "Cell3_MTop_totalTime", "Cell3_MBot_totalTime", "Cell4_MTop_totalTime", "Cell4_MBot_totalTime",
                "Cell5_MTop_totalTime", "Cell5_MBot_totalTime", "Cell6_MTop_totalTime", "Cell6_MBot_totalTime",
                "Total_Num_Cell1_Mtop", "Total_Num_Cell1_Bot", "Total_Num_Cell2_Mtop", "Total_Num_Cell2_Mbot",
                "Total_Num_Cell3_Mtop", "Total_Num_Cell3_MBot", "Total_Num_Cell4_Mtop", "Total_Num_Cell4_MBot",
                "Total_Num_Cell5_Mtop", "Total_Num_Cell5_MBot", "Total_Num_Cell6_Mtop", "Total_Num_Cell6_MBot"
        };

        for (int i = 0; i < variableNames.length; i++) {
            String variableName = variableNames[i];
            int oldValue = variables.get(variableName);
            int newValue = newValues[i];
            variables.put(variableName, newValue);
            support.firePropertyChange(variableName, oldValue, newValue);
        }
    }

    @Override
    public void updateValues(int intValue, double doubleValue, String stringValue) {
        // This method is not used in this example
    }

    // Main method to test the implementation
    /*public static void main(String[] args) {
        int[] initialValues = {
                10, 100, 50, 120, 10, 15, 20, 25,
                30, 35, 40, 45, 50, 55, 60, 65,
                5, 5, 10, 10, 15, 15, 20, 20,
                25, 25, 30, 30, 35, 35, 40, 40
        };

        VariableDisplay variableDisplay = new VariableDisplay(initialValues);

        // Simulate value changes
        try {
            Thread.sleep(2000);
            int[] updatedValues = {
                    20, 200, 100, 240, 20, 30, 40, 50,
                    60, 70, 80, 90, 100, 110, 120, 130,
                    10, 10, 20, 20, 30, 30, 40, 40,
                    50, 50, 60, 60,70, 70, 80, 80
            };
            variableDisplay.updateValues(updatedValues);
            Thread.sleep(2000);
            int[] finalValues = {
                    30, 300, 150, 360, 30, 45, 60, 75,
                    90, 105, 120, 135, 150, 165, 180, 195,
                    15, 15, 30, 30, 45, 45, 60, 60,
                    75, 75, 90, 90, 105, 105, 120, 120
            };
            variableDisplay.updateValues(finalValues);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
