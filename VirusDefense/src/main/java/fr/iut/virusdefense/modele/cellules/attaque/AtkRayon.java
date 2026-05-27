package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Dot;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Ralentissement;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class AtkRayon extends Attaque {

    public AtkRayon(Cellule cellule, int degats){
        super(cellule, degats);
    }

    /**
     * Methode qui attaque quand la cible est a portée et vivante
     */
    @Override
    public void attaque(Maladie m){
        getCellule().getEnvironnement().ajouterRayon(new Rayon(getCellule(), m, getDegats(), 2));
    }

}
