package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class GrandChampignon extends Maladie{
    private int timerSpawnPetitChamp;
    public GrandChampignon(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 250, 0.015, 100);
        timerSpawnPetitChamp=300;
    }

    @Override
    public void agir(){
        if (estVivant()) {
            bouger();

            if (aAtteintLObjectif()) {
                infligerDegatsAuJoueur();
                mourir();
            }else if(timerSpawnPetitChamp<=0){
                timerSpawnPetitChamp=300;
                for (int i = 0; i < (int)((Math.random()*3) + 3); i++) {
                    getEnvironnement().ajouterMaladie(new PetitChampignon(getEnvironnement(), (int)getLigne(), (int)getColonne()));
                }
            }else{
                timerSpawnPetitChamp--;
            }
        }

        if (!estVivant()){
            if(!aAtteintLObjectif()) {
                getEnvironnement().getJoueur().ajouterPc(getRecompense());
            }
            getEnvironnement().getMaladies().remove(this);
        }
    }
}
