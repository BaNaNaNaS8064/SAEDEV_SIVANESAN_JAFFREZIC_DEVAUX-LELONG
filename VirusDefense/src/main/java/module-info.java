module fr.iut.virusdefense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jdk.xml.dom;

    opens fr.iut.virusdefense to javafx.fxml;
    exports fr.iut.virusdefense;

    exports fr.iut.virusdefense.controller;
    exports fr.iut.virusdefense.modele;
    exports fr.iut.virusdefense.modele.cellules;
    exports fr.iut.virusdefense.modele.cellules.attaque;
    exports fr.iut.virusdefense.modele.cellules.reconnaissance;
    exports fr.iut.virusdefense.modele.maladies;
    exports fr.iut.virusdefense.modele.entitesGeneriques;
    exports fr.iut.virusdefense.modele.apparition;
    exports fr.iut.virusdefense.vue;
}