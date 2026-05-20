package fr.iut.virusdefense.modele.spawn;

import fr.iut.virusdefense.modele.Entite;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.BactérieBanale;

public class Generateur extends Entite {

    private int delai;
    private ListeApparition liste;

    public Generateur(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne);
        delai = 0;
        liste = new ListeApparition();
    }

    @Override
    public void agir() {
        delai--;

        if (delai<=0){
            if (liste.resteProchain()) {
                ajouterMaladie(liste.prochaineMaladie());
                delai = liste.prochainDelai();
                liste.avancer();
            }
        }
    }

    public void ajouterMaladie(String codeMaladie){
        switch (codeMaladie){
            case "BB":
                getEnvironnement().ajouter(new BactérieBanale(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
        }
    }
}
