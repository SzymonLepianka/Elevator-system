package main;

import gui_components.ConfigurationPanel;
import gui_components.LoggerPanel;
import utils.Logger;

public class Main {

    /**
     * Entry point of the application.
     *
     * @param args params passed to the application.
     */
    public static void main(String[] args) {

        var logPan = new LoggerPanel();
        logPan.createWindow();
        Logger.getInstance().addLoggingPanel(logPan);

        var panel = new ConfigurationPanel();
        panel.createWindow();
    }
}
