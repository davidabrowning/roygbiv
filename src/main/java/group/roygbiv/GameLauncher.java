package group.roygbiv;

import group.roygbiv.ui.RoygbivApplication;

import javafx.application.Application;

public class GameLauncher {

    // Main method is the entry point for the program
    // and calls this class' go() method
    public static void main(String[] args) {

        System.out.println("Starting program.");
        new GameLauncher().go();
        System.out.println("Ending program.");
    }

    // This method launches the application (RoygbivApplication class)
    private void go() {
        Application.launch(RoygbivApplication.class);
    }

}
