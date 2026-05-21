package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;

public class Konsantre extends Cellule{
    public Konsantre(Environnement env , int ligne , int colonne){
        super(env , ligne ,colonne ,1 , 3.0 , 10 , 50 );
    }
}
