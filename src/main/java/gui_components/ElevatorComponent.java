package gui_components;

import entities.Elevator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ElevatorComponent extends JPanel {

    private static final int MARGIN = 10;

    public ElevatorComponent(Elevator elevator) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
        this.setSize(new Dimension(290 - (MARGIN * 2), 40));

        var elevatorIdText = new JLabel("ID: ");
        elevatorIdText.setAlignmentX(RIGHT_ALIGNMENT);
        this.add(elevatorIdText);

        var elevatorIdLabel = new JLabel(String.valueOf(elevator.getId()));
        elevatorIdLabel.setAlignmentX(RIGHT_ALIGNMENT);
        this.add(elevatorIdLabel);

        var currentFloorText = new JLabel("  Current floor: ");
        currentFloorText.setAlignmentX(CENTER_ALIGNMENT);
        this.add(currentFloorText);

        var currentFloorLabel = new JLabel(String.valueOf(elevator.getCurrentFloor()));
        currentFloorLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(currentFloorLabel);

        var targetFloorText = new JLabel("  Target floor: ");
        targetFloorText.setAlignmentX(LEFT_ALIGNMENT);
        this.add(targetFloorText);

        var targetFloorLabel = new JLabel(String.valueOf(elevator.getTargetFloor()));
        targetFloorLabel.setAlignmentX(LEFT_ALIGNMENT);
        this.add(targetFloorLabel);

        var stateText = new JLabel("  State: ");
        stateText.setAlignmentX(LEFT_ALIGNMENT);
        this.add(stateText);

        var stateLabel = new JLabel(String.valueOf(elevator.getState()));
        stateLabel.setAlignmentX(LEFT_ALIGNMENT);
        this.add(stateLabel);
    }
}
