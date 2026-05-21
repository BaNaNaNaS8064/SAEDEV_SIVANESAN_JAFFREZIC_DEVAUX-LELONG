package fr.iut.virusdefense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {

    private final IntegerProperty pvProperty;
    private final IntegerProperty pcProperty;

    public Joueur(){
        pvProperty = new SimpleIntegerProperty(25);
        pcProperty = new SimpleIntegerProperty(999999);
    }

    public final int getPv() {
        return pvProperty.get();
    }
    public final int getPc(){return pcProperty.get();}

    public final IntegerProperty pvProperty(){
        return pvProperty;
    }
    public final IntegerProperty pcProperty(){
        return pcProperty;
    }

    public final void setPv(int pv){
        pvProperty.setValue(pv);
    }
    public final void setPc(int pc){
        pcProperty.setValue(pc);
    }

    /**
     * Méthode qui enleve les pv quand le joueur subis des degats
     * @param degats les degats qu'il va subir
     */
    public void subisDegats(int degats){
        setPv(Math.max(0, getPv() - degats));
    }

    public void ajouterPC(int pc){
        if (pc>0) this.pcProperty.setValue(this.pcProperty.getValue() + pc);
    }

    public void retirerPC(int pc){
        if (pc>0) this.pcProperty.setValue(Math.max(0, this.pcProperty.getValue() - pc));
    }

}
