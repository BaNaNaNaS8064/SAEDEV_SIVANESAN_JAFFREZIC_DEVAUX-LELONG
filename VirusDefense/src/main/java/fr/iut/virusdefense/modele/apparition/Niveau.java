package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Niveau {

    Environnement environnement;

    private final IntegerProperty numVagueProperty;
    private ArrayList<Vague> vagues;
    private int delaiEntreVagues;
    int delai;

    public Niveau(Environnement environnement){
        this.environnement = environnement;
        delaiEntreVagues = 600;
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
        for (int indVague=0; indVague<100; indVague++){
            vagues.add(new Vague());
            for (int indListeA=0; indListeA<environnement.getCarte().getGenerateurs().size(); indListeA++) {
                vagues.get(indVague).ajouter(new ListeApparition());
                for (int indEnnemi = 0; indEnnemi < 4 * (indVague + 1); indEnnemi++)
                    vagues.get(indVague).getListeApparitions().get(indListeA).ajouter(randomMaladiesCode(), (int) ((Math.random() * 90 + 30)) / (2 * (indVague + 1)));
            }
        }
    }

    private String randomMaladiesCode(){
        return switch ((int)(Math.random() * 3)){
            case 1 -> "Pa";
            case 2 -> "Vi";
            default -> "BB";
        };
    }

    public void passerProchaineVague(){
        numVagueProperty.setValue(getNumVague() + 1);
        environnement.getJoueur().ajouterPc(50 * getNumVague());
        if (resteVague()){
            for (int i=0; i<environnement.getCarte().getGenerateurs().size(); i++)
                environnement.getCarte().getGenerateurs().get(i).setListe(vagues.get(getNumVague()).getListeApparitions().get(i));

            delai = Integer.MAX_VALUE;
        }
    }

    public boolean resteVague(){
        return getNumVague() < vagues.size();
    }

    public void update(){
        if (--delai <= 0 && resteVague())
            passerProchaineVague();

        if (vagues.get(getNumVague()).estTerminee() && delai > delaiEntreVagues)
            delai = delaiEntreVagues;
    }
}
