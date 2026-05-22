package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class RecUnique extends Reconnaissance{

    public RecUnique(Cellule cellule , double portee){
        super(cellule , portee);
    }

//    public void ciblage(){
//        for (Maladie m : getCellule().getEnvironnement().getMaladies())
//            getCibles().add(m);
//    }

    /**
     * Methode qui permet de reconnaitre une bacterie de la prendre comme cible
     */
    public void changerCible(){
        int i = 0;
        Maladie m;

        cibles.clear();

        while (!aUneCible() && i < getCellule().getEnvironnement().getMaladies().size()){
            m = getCellule().getEnvironnement().getMaladies().get(i);

            if (m.estVivant() && aPortee(m))
                cibles.add(m);

            i++;
        }
    }

}
