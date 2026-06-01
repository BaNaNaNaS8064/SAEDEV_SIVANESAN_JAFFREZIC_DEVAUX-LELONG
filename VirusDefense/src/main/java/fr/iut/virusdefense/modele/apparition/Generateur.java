package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

import java.util.ArrayList;

public class Generateur extends Entite {

    private int delai;
    private ArrayList<ListeApparition> listes;

    public Generateur(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne);
        delai = 0;
        listes = new ArrayList<>();
    }

    public void ajouter(ListeApparition liste) {
        listes.add(liste);
    }

    @Override
    public void agir() {
        delai--;

        if (delai<=0){
            ListeApparition liste;

            for (int i = listes.size() - 1; i >= 0; i--) {
                liste = listes.get(i);
                if (liste.resteProchain()) {
                    ajouterMaladie(liste.prochaineMaladie());
                    delai = liste.prochainDelai();
                    liste.avancer();
                }
                else {
                    listes.remove(i);
                }
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
