package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.*;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkProjectile extends Attaque{

    public AtkProjectile(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, degats, cibles);
    }

    public void attaque(Maladie m){
        getEnvironnement().ajouterProjectile(new Projectile(getEnvironnement(), getLigne(), getColonne(), m, getDegats(), getAlterations()));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}