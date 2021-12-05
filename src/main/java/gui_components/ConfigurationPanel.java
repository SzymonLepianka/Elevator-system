package gui_components;

import world.World;
import world.WorldConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ConfigurationPanel {
    private final JFrame mainFrame = new JFrame("Elevator System");
    JComboBox<String> numberOfElevatorsSelectionComboBox = new JComboBox<>();
    JComboBox<String> algorithmSelectionComboBox = new JComboBox<>();

    public void createWindow() {
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout(2, 3));

        JPanel mainSelectionPanel = new JPanel();
        mainFrame.add(mainSelectionPanel);

        //-------------------

        mainSelectionPanel.add(new JLabel("Select algorithm: "));

        //drop-down list with algorithm selection
        this.algorithmSelectionComboBox = new JComboBox<>(World.getInstance().getConfig().availableAlgorithms());
        mainSelectionPanel.add(algorithmSelectionComboBox);

        //-------------------

        mainSelectionPanel.add(new JLabel("Select the number of elevators: "));

        String[] availableNumbersOfElevators = new String[16];
        for (int i = 0; i < availableNumbersOfElevators.length; i++) {
            availableNumbersOfElevators[i] = String.valueOf(i + 1);
        }

        //drop-down list with number of elevators selection
        this.numberOfElevatorsSelectionComboBox = new JComboBox<>(availableNumbersOfElevators);
        mainSelectionPanel.add(numberOfElevatorsSelectionComboBox);

        //-------------------

        var runSimulationButton = new Button("Run the simulation!");
        runSimulationButton.addActionListener(e -> runSimulationButtonClicked());
        mainSelectionPanel.add(runSimulationButton);

        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void runSimulationButtonClicked() {
        var config = World.getInstance().getConfig();
        setDataFromConfigurationPanel(config);

        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));

        var mainPanel = new MainPanel();
        mainPanel.createMapWindow();

        var callingPanel = new CallingPanel();
        callingPanel.createMapWindow();

    }

    private void setDataFromConfigurationPanel(WorldConfiguration config) {
        config.setNumberOfElevators(Integer.parseInt(numberOfElevatorsSelectionComboBox.getSelectedItem().toString()));
        config.setChosenAlgorithm(WorldConfiguration.Algorithm.valueOf(algorithmSelectionComboBox.getSelectedItem().toString()));
    }
}
