package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Rayon extends EntiteAtk {
    private int age;
    private final int ageMaximal;

    public Rayon(Cellule cellule, Maladie cible, int degats, int ageMaximal, List<Alteration> alterations) {
        super(cellule.getEnvironnement(), cellule.getLigne(), cellule.getColonne(), degats, alterations, cible);

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
