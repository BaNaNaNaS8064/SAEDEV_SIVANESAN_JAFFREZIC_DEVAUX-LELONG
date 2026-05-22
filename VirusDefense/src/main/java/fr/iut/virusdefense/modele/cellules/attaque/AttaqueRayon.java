package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Rayon;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class AttaqueRayon extends Attaque {

    public AttaqueRayon(Cellule cellule, int degats){
        super(cellule , degats);
    }

    /**
     * Methode qui attaque quand la cible est a portée et vivante
     */
    public void attaque(){
        for (Maladie m : getCellule().getReconnaissance().getCibles()){
            getCellule().getEnvironnement().ajouterTir(new Rayon(getCellule().getEnvironnement() , getCellule().getLigne() , getCellule().getColonne() , m.getLigne() , m.getColonne()));
            m.prendreDegats(getDegats());
        }
    }

}
