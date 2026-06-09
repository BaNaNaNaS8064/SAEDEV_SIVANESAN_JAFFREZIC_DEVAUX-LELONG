package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class GrandChampignon extends Maladie{
    private int delaiInvocation;
    public GrandChampignon(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 250, 0.015, 100);
        delaiInvocation = 300;
    }

    @Override
    public void capaciteActive() {
        if(--delaiInvocation <= 0){
            delaiInvocation = 300;
            for (int i = 0; i < (int)((Math.random()*3) + 3); i++)
                getEnvironnement().ajouterMaladie(new PetitChampignon(getEnvironnement(), (int)getLigne(), (int)getColonne()));
        }
    }
}
