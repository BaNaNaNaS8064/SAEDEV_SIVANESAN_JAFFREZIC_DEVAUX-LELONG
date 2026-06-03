package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Projectile;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class AtkProjectile extends Attaque{
    public AtkProjectile(Cellule cellule, int degats){
        super(cellule, degats);
    }

    public void attaque(Maladie m){
        getCellule().getEnvironnement().ajouterProjectile(new Projectile(getCellule(), m, getDegats(), getAlterations()));
    }

    public void attaqueCibles(){
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }
}