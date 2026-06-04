package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.Tumeur;

import java.util.ArrayList;
import java.util.List;

public class Zone extends EntiteAtk{
    private ArrayList<Maladie> cibles;
    private final int degats;
    private int age;
    private final int ageMaximal;
    private final double rayonZone;


    public Zone(Entite e1, ArrayList<Maladie> cibles, int degats, int ageMaximal, List<Alteration> alterations, double portee) {
        super(e1.getEnvironnement(), e1.getLigne(), e1.getColonne(), alterations);
        age = 0;
        this.ageMaximal = ageMaximal;

        this.degats = degats;
        this.cibles = cibles;
        this.rayonZone = portee;

        donnerAlterations();
        infligerDegats();
    }

    public double getRayonZone() {
        return rayonZone;
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    @Override
    public void infligerDegats() {
        for (Maladie cible : cibles){
            cible.prendreDegats(degats);
        }
    }

    @Override
    public void donnerAlterations() {
        for (Maladie cible : cibles) {
            if (!(cible instanceof Tumeur)) {
                for (Alteration alt : getAlterations()) {
                    alt.setMaladie(cible);
                    getEnvironnement().getAlterations().add(alt);
                }
            }
        }
    }


    @Override
    public void agir() {
        age++;
    }
}
