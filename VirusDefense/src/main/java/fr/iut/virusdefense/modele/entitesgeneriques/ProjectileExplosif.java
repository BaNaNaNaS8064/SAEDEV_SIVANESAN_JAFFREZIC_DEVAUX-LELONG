package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecBrulure;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class ProjectileExplosif extends Projectile{
    private final double rayonZonePortee;

    public ProjectileExplosif(Environnement environnement, double ligne, double colonne, Maladie cible, double degats, List<Alteration> alterations) {
        super(environnement, ligne, colonne, cible, degats, alterations);
        this.rayonZonePortee = 3.0;
    }

    @Override
    public void attaquer(){
        Reconnaissance reconnaissance = new RecBrulure(getLigne(),getColonne(),getEnvironnement().getMaladies(),rayonZonePortee,Integer.MAX_VALUE);
        Zone explosion = new ZonePersistante(getEnvironnement(),getLigne(),getColonne(),getCibles(),getDegats(),100,getAlterations(),rayonZonePortee,reconnaissance);
        getEnvironnement().ajouterZone(explosion);
    }


}
