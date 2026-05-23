package fr.iut.virusdefense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {

    private final IntegerProperty pvProperty;
    private final IntegerProperty pcProperty;

    public Joueur(){
        pvProperty = new SimpleIntegerProperty(25);
        pcProperty = new SimpleIntegerProperty(100);
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

    public final int getPc(){
        return pcProperty.get();
    }

    public final IntegerProperty pcProperty(){
        return pcProperty;
    }

    public final void setPc(int pc){
        pcProperty.setValue(pc);
    }

    /**
     * Méthode qui enleve les pv quand le joueur subis des degats
     * @param montant les degats qu'il va subir
     */
    public void retirerPv(int montant){
        if (montant>0)
            setPv(Math.max(0, getPv() - montant));
    }

    public void ajouterPc(int pc){
        if (pc>0)
            setPc(getPc() + pc);
    }

    public void retirerPc(int pc){
        if (pc>0)
            setPc(getPc() - pc);
    }
}
