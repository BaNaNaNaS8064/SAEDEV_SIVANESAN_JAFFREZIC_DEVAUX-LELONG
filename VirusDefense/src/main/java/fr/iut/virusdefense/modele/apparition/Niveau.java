package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;

import java.util.ArrayList;

public class Niveau {

    Environnement environnement;

    int curseur;
    private ArrayList<Vague> vagues;

    public Niveau(Environnement environnement){
        this.environnement = environnement;
        curseur = -1;
        initVagues();
        passerProchaineVague();
    }

    public void initVagues(){
        vagues = new ArrayList<>();
        for (int i=0; i<10; i++){
            vagues.add(new Vague());
            vagues.get(i).ajouter(new ListeApparition());
            for (int j=0; j<4; j++)
                vagues.get(i).getListeApparitions().get(0).ajouter("BB", (int)((Math.random()*90+30)) / (i+1));
            vagues.get(i).getListeApparitions().get(0).ajouter("BB", 600);
        }
    }

    public void passerProchaineVague(){
        curseur++;
        if (resteVague()){
            for (int i=0; i<environnement.getCarte().getGenerateurs().size(); i++){
                environnement.getCarte().getGenerateurs().get(i).setListe(vagues.get(curseur).getListeApparitions().get(i));
            }
        }
    }

    public boolean resteVague(){
        return curseur < vagues.size();
    }

    public void update(){
        if (resteVague() && vagues.get(curseur).estTerminee())
            passerProchaineVague();
    }

}
