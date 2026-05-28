package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;

public class AtkRayonBase extends AtkRayon {

    public AtkRayonBase(Cellule cellule, int degats){
        super(cellule, degats);
    }

    @Override
    public void attaqueCibles(){
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }
}
