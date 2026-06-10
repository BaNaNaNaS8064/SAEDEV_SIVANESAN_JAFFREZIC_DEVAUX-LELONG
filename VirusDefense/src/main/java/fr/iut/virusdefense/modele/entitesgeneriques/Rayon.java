package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Rayon extends Attaque {
    private int age;
    private final int ageMaximal;

    public Rayon(Environnement environnement, double ligne, double colonne, Maladie cible, double degats, int ageMaximal, List<Alteration> alterations) {
        super(environnement, ligne, colonne, degats, alterations, cible);
        age = 0;
        this.ageMaximal = ageMaximal;

        donnerAlterations();
        infligerDegats();
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    @Override
    public void agir(){
        age++;
    }
}
