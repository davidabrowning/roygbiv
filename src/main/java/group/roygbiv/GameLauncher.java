package group.roygbiv;

import group.roygbiv.ui.RoygbivApplication;

import javafx.application.Application;

public class GameLauncher {

    public static void main(String[] args) {

        System.out.println("Starting program.");
        new GameLauncher().go();
        System.out.println("Ending program.");
    }

    private void go() {
        Application.launch(RoygbivApplication.class);
    }

}
