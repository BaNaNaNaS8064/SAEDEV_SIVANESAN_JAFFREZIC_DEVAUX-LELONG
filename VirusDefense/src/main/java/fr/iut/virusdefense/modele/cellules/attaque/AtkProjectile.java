package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Projectile;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkProjectile extends Attaque{
    int degats;

    public AtkProjectile(Environnement environnement, double ligne, double colonne, int degats, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, cibles);
        this.degats = degats;
    }

    public void attaque(Maladie m){
        getEnvironnement().ajouterProjectile(new Projectile(getEnvironnement(), getLigne(), getColonne(), m, degats, 2, getAlterations()));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}