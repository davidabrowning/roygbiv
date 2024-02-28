module group.roygbiv {
    requires javafx.controls;

    exports group.roygbiv;
    exports group.roygbiv.logic;
    exports group.roygbiv.ui;
    opens group.roygbiv.ui to javafx.fxml;
}