module RMIChatGUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    exports sample;
    opens sample;
}