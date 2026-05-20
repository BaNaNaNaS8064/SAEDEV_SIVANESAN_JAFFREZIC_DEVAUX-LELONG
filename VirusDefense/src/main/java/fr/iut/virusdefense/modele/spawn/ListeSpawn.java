package fr.iut.virusdefense.modele.spawn;

import java.util.ArrayList;

public class ListeSpawn {

    private int curseur;
    private ArrayList<String> listeMaladies;
    private ArrayList<Integer> listeDelais;

    public ListeSpawn(){
        curseur = 0;
        listeMaladies = new ArrayList<>();
        listeDelais = new ArrayList<>();
        initMaladies();
        initDelais();
    }

    private void initMaladies(){
        listeMaladies.add("BB");
        listeMaladies.add("BB");
        listeMaladies.add("BB");
        listeMaladies.add("BB");
    }

    private void initDelais(){
        listeDelais.add(60);
        listeDelais.add(180);
        listeDelais.add(0);
        listeDelais.add(10);
    }

    public boolean resteProchain(){
        return curseur < Math.min(listeMaladies.size(), listeDelais.size());
    }

    public String prochaineMaladie(){
        return listeMaladies.get(curseur);
    }

    public int prochainDelai(){
        return listeDelais.get(curseur);
    }

    public void avancer(){
        curseur++;
    }

}
