package fr.iut.virusdefense.modele.apparition;

import java.util.ArrayList;

public class Vague {

    private final ArrayList<ListeApparition> listeApparitions;

    public Vague(){
        listeApparitions = new ArrayList<>();
    }

    public ArrayList<ListeApparition> getListeApparitions() {
        return listeApparitions;
    }

    public void ajouter(ListeApparition listeApparition){
        listeApparitions.add(listeApparition);
    }

    public boolean estTerminee(){
        for (ListeApparition la : listeApparitions)
            if (la.resteProchain())
                return false;

        return true;
    }

}
