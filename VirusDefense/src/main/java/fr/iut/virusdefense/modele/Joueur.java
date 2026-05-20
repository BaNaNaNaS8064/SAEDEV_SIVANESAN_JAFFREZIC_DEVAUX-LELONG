package fr.iut.virusdefense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {

    private final IntegerProperty pvProperty;

    public Joueur(){
        pvProperty = new SimpleIntegerProperty(25);
    }

    public final int getPv() {
        return pvProperty.get();
    }

    public final IntegerProperty pvProperty(){
        return pvProperty;
    }

    public final void setPv(int pv){
        pvProperty.setValue(pv);
    }

    /**
     * Méthode qui enleve les pv quand le joueur subis des degats
     * @param degats les degats qu'il va subir
     */
    public void subisDegats(int degats){
        setPv(Math.max(0, getPv() - degats));
    }

}
