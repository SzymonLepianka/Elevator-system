package gui_components;

import entities.Elevator;
import simulation.ElevatorUpdater;
import world.World;
import world.WorldConfiguration;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    private final JFrame frame = new JFrame("Elevator's panel");
    private final JButton stepButton = new JButton("Step");

    public void createMapWindow() {
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(1, 2));

        createElevators();

//----------------------------------------------------
        JPanel elevatorsPanel = new JPanel();
        frame.add(elevatorsPanel);

        var scrollContent = new JPanel();
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));

        var districtScrollPane = new JScrollPane(scrollContent);
        districtScrollPane.setPreferredSize(new Dimension(500, 300));
        districtScrollPane.setBounds(300, 0, 300, 500);
        districtScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        districtScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        elevatorsPanel.add(districtScrollPane);

        revalidateElevators(scrollContent);

//----------------------------------------------------

        stepButton.setMaximumSize(new Dimension(50, 50));
        stepButton.addActionListener(e -> {
            World.getInstance().incrementSimulationStep();
            var chosenAlgorithm = World.getInstance().getConfig().getChosenAlgorithm();
            if (chosenAlgorithm == WorldConfiguration.Algorithm.FIRST_COME_FIRST_SERVE) {
                ElevatorUpdater.assignRequestsFcfs();
            } else if (chosenAlgorithm == WorldConfiguration.Algorithm.OWN_PRIMITIVE_COST_CALCULATION) {
                ElevatorUpdater.assignRequestsPrimitiveCost();
            }
            ElevatorUpdater.performActionOfElevators();
            revalidateElevators(scrollContent);
        });
        JPanel mainPanel = new JPanel();
        mainPanel.add(stepButton);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private void revalidateElevators(JPanel scrollContent) {
        var allElevators = World.getInstance().getElevators();
        scrollContent.removeAll();
        for (var elevator : allElevators) {
            scrollContent.add(new ElevatorComponent(elevator));
        }
        scrollContent.revalidate();
    }

    private void createElevators() {
        var world = World.getInstance();
        for (int i = 0; i < world.getConfig().getNumberOfElevators(); i++) {
            var newElevator = new Elevator(i);
            newElevator.setState(Elevator.State.NOT_MOVING);
            world.addEntity(newElevator);
        }
    }
}
