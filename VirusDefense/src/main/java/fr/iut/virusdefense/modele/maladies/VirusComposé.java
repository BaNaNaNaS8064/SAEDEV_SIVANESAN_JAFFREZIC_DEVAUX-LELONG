package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class VirusComposé extends Maladie{
    private int stade;
    public VirusComposé(Environnement environnement, int ligne, int colonne){
        this(environnement, ligne, colonne, 1);
    }

    private VirusComposé(Environnement environnement, int ligne, int colonne, int stade){
        super(environnement, ligne, colonne, 80/(int)(Math.pow(2,(stade-1))), 0.03, 24/(int)(Math.pow(2,(stade-1))));
        this.stade = stade;
    }

    @Override
    public void agir(){
        if (estVivant()) {
            bouger();

            if (aAtteintLObjectif()) {
                infligerDegatsAuJoueur();
                mourir();
            }
        }

        if (!estVivant()){
            if(!aAtteintLObjectif()) {
                getEnvironnement().getJoueur().ajouterPc(getRecompense());
                if(stade<3)
                    for (int i =0; i<2;i++)
                        getEnvironnement().getMaladies().add(new VirusComposé(getEnvironnement(), (int)getLigne(), (int)getColonne(), stade+1));
            }
            getEnvironnement().getMaladies().remove(this);
        }
    }
}
