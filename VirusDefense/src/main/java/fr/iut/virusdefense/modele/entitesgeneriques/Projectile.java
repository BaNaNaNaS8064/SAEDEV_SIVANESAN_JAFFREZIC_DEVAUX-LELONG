package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.Tumeur;

import java.util.List;

public class Projectile extends EntiteAtk {
    private boolean cibleTouché;

    public Projectile(Environnement environnement, double ligne, double colonne, Maladie cible, int degats, int ageMaximal, List<Alteration> alterations){
        super(environnement, ligne, colonne, degats, alterations, cible);

        this.cibleTouché = false;
    }

    public boolean getCibleTouché(){
        return cibleTouché;
    }

    @Override
    public void agir(){
        setLigne(getLigne() + 0.1 * Double.compare(getCibles().get(0).getLigne(), getLigne()));
        setColonne(getColonne() + 0.1 * Double.compare(getCibles().get(0).getColonne(), getColonne()));

        if(Math.abs(getCibles().get(0).getLigne() - getLigne()) <= 0.5 && Math.abs(getCibles().get(0).getColonne() - getColonne()) <= 0.5){
            infligerDegats();
            donnerAlterations();
            cibleTouché = true;
        }
    }
}
