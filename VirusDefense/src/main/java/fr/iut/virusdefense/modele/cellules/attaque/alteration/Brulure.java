package fr.iut.virusdefense.modele.cellules.attaque.alteration;

public class Brulure extends Dot{
    public Brulure(int degats, int dureeDeVie){
        super(degats ,dureeDeVie);
    }

    public Brulure copieAlteration(){
        return new Brulure(getDegats() , getDureeDeVie());
    }
}
