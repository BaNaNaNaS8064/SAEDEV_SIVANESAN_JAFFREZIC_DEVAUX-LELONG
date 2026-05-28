package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class AtkZone extends Attaque{
    public AtkZone(Cellule cellule, int degats){
        super(cellule, degats);
    }


    @Override
    public void attaqueCibles(){
        getCellule().getEnvironnement().ajouterZone(new Zone(getCellule(),getCellule().getReconnaissance().getCibles() , getDegats() , 10 , getAlterations(), getCellule().getReconnaissance().getPortee()));
    }
}
