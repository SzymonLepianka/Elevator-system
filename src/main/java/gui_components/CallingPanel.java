package gui_components;

import entities.Request;
import utils.Logger;
import world.World;

import javax.swing.*;
import java.awt.*;

public class CallingPanel {
    private final JFrame frame = new JFrame("Calling's panel");
    private final JButton pickUpButton = new JButton("Pick up");
    JComboBox<String> numberOfCurrentFloorComboBox = new JComboBox<>();
    JComboBox<String> numberOfTargetFloorComboBox = new JComboBox<>();

    public void createMapWindow() {
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        String[] availableNumbersOfFloors = new String[10];
        for (int i = 0; i < availableNumbersOfFloors.length; i++) {
            availableNumbersOfFloors[i] = String.valueOf(i);
        }
        //drop-down list with number of current floor
        this.numberOfCurrentFloorComboBox = new JComboBox<>(availableNumbersOfFloors);

        //drop-down list with number of target floor
        this.numberOfTargetFloorComboBox = new JComboBox<>(availableNumbersOfFloors);

        pickUpButton.setMaximumSize(new Dimension(50, 50));
        pickUpButton.addActionListener(e -> {
            int currentFloor = Integer.parseInt(numberOfCurrentFloorComboBox.getSelectedItem().toString());
            int targetFloor = Integer.parseInt(numberOfTargetFloorComboBox.getSelectedItem().toString());
            if (currentFloor != targetFloor) {
                var newRequest = new Request(currentFloor, targetFloor);
                Logger.getInstance().logNewMessage("take new request");
                World.getInstance().addEntity(newRequest);
            }
        });
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);

        var currentFloorText = new JTextArea("Current floor: ");
        currentFloorText.setEditable(false);
        mainPanel.add(currentFloorText);
        mainPanel.add(numberOfCurrentFloorComboBox);
        var targetFloorText = new JTextArea("Target floor: ");
        targetFloorText.setEditable(false);
        mainPanel.add(targetFloorText);
        mainPanel.add(numberOfTargetFloorComboBox);
        mainPanel.add(pickUpButton);
        frame.setVisible(true);
    }
}
