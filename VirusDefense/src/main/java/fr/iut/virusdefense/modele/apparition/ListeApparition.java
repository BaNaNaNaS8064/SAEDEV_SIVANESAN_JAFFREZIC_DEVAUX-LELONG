package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

import java.util.ArrayList;

public class ListeApparition {

    private int curseur;
    private ArrayList<CodeMaladie> listeMaladies;
    private ArrayList<Integer> listeDelais;

    public ListeApparition(){
        curseur = 0;
        listeMaladies = new ArrayList<>();
        listeDelais = new ArrayList<>();
    }

    public void ajouter(CodeMaladie codeMaladie, int delai){
        listeMaladies.add(codeMaladie);
        listeDelais.add(delai);
    }

    public boolean resteProchain(){
        return curseur < Math.min(listeMaladies.size(), listeDelais.size());
    }

    public CodeMaladie prochaineMaladie(){
        return listeMaladies.get(curseur);
    }

    public int prochainDelai(){
        return listeDelais.get(curseur);
    }

    public void avancer(){
        curseur++;
    }

}
