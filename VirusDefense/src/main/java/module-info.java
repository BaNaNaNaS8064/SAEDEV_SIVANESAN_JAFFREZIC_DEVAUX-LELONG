module fr.iut.virusdefense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fr.iut.virusdefense to javafx.fxml;
    exports fr.iut.virusdefense;
    exports fr.iut.virusdefense.controller;
}