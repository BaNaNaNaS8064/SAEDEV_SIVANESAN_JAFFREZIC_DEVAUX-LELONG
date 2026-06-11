package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.ProjectileExplosif;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkProjectileExplosif extends Attaque{
    private int tempsZone;
    private final double rayonZonePortee;
    private double degatsInstantane;
    private double rayonInstantane;


    public AtkProjectileExplosif(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles,double rayonZonePortee, double degatsInstantane, double rayonInstantane ,int tempsZone){
        super(environnement, ligne, colonne, degats, cibles);
        this.tempsZone = tempsZone;
        this.rayonZonePortee = rayonZonePortee;
        this.degatsInstantane = degatsInstantane;
        this.rayonInstantane = rayonInstantane;
    }

    public int getTempsZone() {
        return tempsZone;
    }

    public void setTempsZone(int tempsZone) {
        this.tempsZone = tempsZone;
    }

    public void attaque(Maladie m){
        getEnvironnement().ajouterProjectile(new ProjectileExplosif(getEnvironnement(), getLigne(), getColonne(), m, getDegats(), getAlterations(),rayonZonePortee,degatsInstantane,rayonInstantane, tempsZone));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}
