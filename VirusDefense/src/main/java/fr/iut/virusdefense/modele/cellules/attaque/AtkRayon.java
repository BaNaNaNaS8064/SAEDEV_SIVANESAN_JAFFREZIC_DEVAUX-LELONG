package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class AtkRayon extends Attaque {

    public AtkRayon(Environnement environnement, double ligne, double colonne, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, cibles);
    }

    public final void attaque(Maladie m, int degats){
        getEnvironnement().ajouterRayon(new Rayon(getEnvironnement(), getLigne(), getColonne(), m, degats, 2, getAlterations()));
    }
}
