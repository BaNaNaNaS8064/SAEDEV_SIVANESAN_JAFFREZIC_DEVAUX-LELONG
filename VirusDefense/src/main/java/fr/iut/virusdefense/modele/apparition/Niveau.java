package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Random;

public class Niveau {

    Environnement environnement;

    private final IntegerProperty numVagueProperty;
    private ArrayList<Vague> vagues;
    int delai;

    public Niveau(Environnement environnement){
        this.environnement = environnement;
        delai = Integer.MAX_VALUE;
        numVagueProperty = new SimpleIntegerProperty(-1);
        initVagues();
        passerProchaineVague();
    }

    public final int getNumVague(){
        return numVagueProperty.getValue();
    }

    public final IntegerProperty numVagueProperty(){
        return numVagueProperty;
    }

    public final void setNumVague(int numVague){
        numVagueProperty.setValue(numVague);
    }

    public void initVagues(){
        vagues = new ArrayList<>();
        for (int i=0; i<100; i++){
            vagues.add(new Vague());
            vagues.get(i).ajouter(new ListeApparition());
            for (int j=0; j<4*(i+1); j++)
                vagues.get(i).getListeApparitions().get(0).ajouter(randomMaladiesCode(), (int)((Math.random()*90+30)) / (2*(i+1)));
        }
    }

    private String randomMaladiesCode(){
        switch ((int)(Math.random() * 3)){
            case 1 -> { return "Pa"; }
            case 2 -> { return "Vi"; }
            default -> { return "BB"; }

        }

    }

    public void passerProchaineVague(){
        numVagueProperty.setValue(getNumVague() + 1);
        environnement.getJoueur().ajouterPC(50 * getNumVague());
        if (resteVague()){
            for (int i=0; i<environnement.getCarte().getGenerateurs().size(); i++)
                environnement.getCarte().getGenerateurs().get(i).setListe(vagues.get(getNumVague()).getListeApparitions().get(i));

            delai = 600;
        }
    }

    public boolean resteVague(){
        return getNumVague() < vagues.size();
    }

    public void update(){
        delai--;

        if (delai <= 0) {
            delai = Integer.MAX_VALUE;
            passerProchaineVague();
        }

        if (resteVague() && vagues.get(getNumVague()).estTerminee() && delai > 600)
            delai = 600;
    }
}
