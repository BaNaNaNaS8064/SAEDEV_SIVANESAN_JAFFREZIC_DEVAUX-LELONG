package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Niveau {

    Environnement environnement;

    private IntegerProperty curseur;
    private ArrayList<Vague> vagues;
    int delai;

    public Niveau(Environnement environnement){
        this.environnement = environnement;
        delai = Integer.MAX_VALUE;
        curseur = new SimpleIntegerProperty(-1);
        initVagues();
        passerProchaineVague();
    }

    public IntegerProperty getCurseur(){return curseur;}

    public void initVagues(){
        vagues = new ArrayList<>();
        for (int i=0; i<100; i++){
            vagues.add(new Vague());
            vagues.get(i).ajouter(new ListeApparition());
            for (int j=0; j<4*(i+1); j++)
                vagues.get(i).getListeApparitions().get(0).ajouter("BB", (int)((Math.random()*90+30)) / (2*(i+1)));
        }
    }

    public void passerProchaineVague(){
        curseur.setValue(curseur.getValue() + 1);
        if (resteVague()){
            for (int i=0; i<environnement.getCarte().getGenerateurs().size(); i++)
                environnement.getCarte().getGenerateurs().get(i).setListe(vagues.get(curseur.getValue()).getListeApparitions().get(i));

            delai = 600;
        }
    }

    public boolean resteVague(){
        return curseur.getValue() < vagues.size();
    }

    public void update(){
        delai--;

        if (delai <= 0) {
            delai = Integer.MAX_VALUE;
            passerProchaineVague();
        }

        if (resteVague() && vagues.get(curseur.getValue()).estTerminee() && delai > 600)
            delai = 600;
    }
}
