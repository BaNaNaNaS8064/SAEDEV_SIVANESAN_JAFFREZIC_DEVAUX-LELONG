package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class ZonePersistante extends Zone{
    private final Reconnaissance reconnaissance;
    private final int delai;
    public ZonePersistante(Environnement environnement, double ligne, double colonne, List<Maladie> cibles, double degats, int ageMaximal, List<Alteration> alterations, double rayonZone, Reconnaissance reconnaissance) {
        super(environnement, ligne, colonne, cibles, degats, ageMaximal, alterations, rayonZone);
        this.reconnaissance = reconnaissance;
        this.delai = 10;
    }

    public void actualiser(){
        if (getAge()%delai == 0) {
            reconnaissance.actualiser();
            setCibles(reconnaissance.getCibles());
            attaquer();
        }
    }

    @Override
    public void effetSpecial() {
        actualiser();
        super.agir();
    }
}
