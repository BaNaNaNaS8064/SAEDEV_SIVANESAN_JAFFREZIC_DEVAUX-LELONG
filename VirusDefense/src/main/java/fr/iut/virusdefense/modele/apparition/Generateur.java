package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Entite;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.BacterieBanale;

public class Generateur extends Entite {

    private int delai;
    private ListeApparition liste;

    public Generateur(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne);
        delai = 0;
        liste = new ListeApparition();
    }

    public void setListe(ListeApparition liste) {
        this.liste = liste;
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
                getEnvironnement().ajouter(new BacterieBanale(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
        }
    }
}
