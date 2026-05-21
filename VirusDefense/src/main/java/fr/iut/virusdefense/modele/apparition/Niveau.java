package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;

import java.util.ArrayList;

public class Niveau {

    Environnement environnement;

    int curseur;
    private ArrayList<Vague> vagues;
    int delai;

    public Niveau(Environnement environnement){
        this.environnement = environnement;
        curseur = -1;
        delai = Integer.MAX_VALUE;
        initVagues();
        passerProchaineVague();
    }

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
        curseur++;
        if (resteVague()){
            for (int i=0; i<environnement.getCarte().getGenerateurs().size(); i++)
                environnement.getCarte().getGenerateurs().get(i).setListe(vagues.get(curseur).getListeApparitions().get(i));

            delai = 600;
        }
    }

    public boolean resteVague(){
        return curseur < vagues.size();
    }

    public void update(){
        delai--;

        if (delai <= 0){
            delai = Integer.MAX_VALUE;
            passerProchaineVague();
        }

        if (resteVague() && vagues.get(curseur).estTerminee() && delai > 600)
            delai = 600;
    }

}
