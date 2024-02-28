module group.roygbiv {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.roygbiv to javafx.fxml;
    exports group.roygbiv;
    exports group.roygbiv.logic;
    opens group.roygbiv.logic to javafx.fxml;
    exports group.roygbiv.ui;
    opens group.roygbiv.ui to javafx.fxml;
}