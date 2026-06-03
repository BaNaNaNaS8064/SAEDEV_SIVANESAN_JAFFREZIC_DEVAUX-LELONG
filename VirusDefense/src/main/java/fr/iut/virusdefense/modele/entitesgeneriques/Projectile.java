package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.Tumeur;

import java.util.List;

public class Projectile extends EntiteAtk {
    private final Maladie cible;

    private final int degats;
    private boolean cibleTouché;

    public Projectile(Entite e1, Maladie e2, int degats, List<Alteration> alterations){
        super(e1.getEnvironnement(), e1.getLigne(), e1.getColonne(), alterations);
        this.cible = e2;

        this.degats = degats;
        this.cibleTouché = false;
    }

    public Entite getCible(){
        return cible;
    }

    public boolean getCibleTouché(){
        return cibleTouché;
    }

    public void infligerDegats(){
        cible.prendreDegats(degats);
    }

    public void donnerAlterations(){
        if (!(cible instanceof Tumeur))
            for (Alteration alt : getAlterations()){
                alt.setMaladie(cible);
                getEnvironnement().getAlterations().add(alt);
            }
    }

    @Override
    public void agir(){
        setLigne(getLigne() + 0.1 * Double.compare(cible.getLigne(), getLigne()));
        setColonne(getColonne() + 0.1 * Double.compare(cible.getColonne(), getColonne()));

        if(Math.abs(cible.getLigne() - getLigne()) <= 0.5 && Math.abs(cible.getColonne() - getColonne()) <= 0.5){
            infligerDegats();
            donnerAlterations();
            cibleTouché = true;
        }
    }
}
