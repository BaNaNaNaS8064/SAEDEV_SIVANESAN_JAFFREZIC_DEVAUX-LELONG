package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;

public class Rizcocher extends Cellule {
    public Rizcocher(Environnement env , int ligne , int colonne){
        super(env , ligne ,colonne ,1 , 3.0 , 10 , 50 );
    }
}
