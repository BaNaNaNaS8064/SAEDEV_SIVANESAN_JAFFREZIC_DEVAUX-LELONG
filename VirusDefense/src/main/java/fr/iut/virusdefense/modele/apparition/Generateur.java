package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

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

    public void ajouterMaladie(CodeMaladie codeMaladie){
        switch (codeMaladie){
            case BACTERIEBANALE:
                getEnvironnement().ajouterMaladie(new BacterieBanale(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
            case PARASITE:
                getEnvironnement().ajouterMaladie(new Parasite(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
            case VIRUS:
                getEnvironnement().ajouterMaladie(new Virus(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
            case VIRUSCOMPOSE:
                getEnvironnement().ajouterMaladie(new VirusComposé(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
            case PETITCHAMPIGNON:
                getEnvironnement().ajouterMaladie(new PetitChampignon(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
            case GRANDCHAMPIGNON:
                getEnvironnement().ajouterMaladie(new GrandChampignon(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
            case TUMEUR:
                getEnvironnement().ajouterMaladie(new Tumeur(getEnvironnement(), (int) getLigne(), (int) getColonne()));
                break;
        }
    }
}
