package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.Tumeur;

import java.util.List;

public abstract class EntiteAtk extends Entite{
    private final List<Maladie> cibles;
    private final List<Alteration> alterations;
    private final int degats;

    public EntiteAtk(Environnement environnement, double ligne, double colonne, int degats, List<Alteration> alterations, Maladie cible){
        this(environnement, ligne, colonne, degats, alterations, List.of(cible));
    }

    public EntiteAtk(Environnement environnement, double ligne, double colonne, int degats, List<Alteration> alterations, List<Maladie> cibles) {
        super(environnement, ligne, colonne);
        this.degats = degats;
        this.alterations = alterations;
        this.cibles = cibles;
    }

    public int getDegats(){
        return degats;
    }

    public List<Alteration> getAlterations() {
        return alterations;
    }

    public List<Maladie> getCibles(){
        return cibles;
    }

    public final void infligerDegats() {
        for (Maladie cible : cibles){
            cible.prendreDegats(getDegats());
        }
    }

    public final void donnerAlterations() {
        for (Maladie cible : cibles) {
            if (!(cible instanceof Tumeur)) {
                for (Alteration alt : getAlterations()) {
                    alt.setMaladie(cible);
                    getEnvironnement().getAlterations().add(alt);
                }
            }
        }
    }
}
