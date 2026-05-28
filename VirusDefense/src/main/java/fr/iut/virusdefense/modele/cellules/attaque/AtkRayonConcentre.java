package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

public class AtkRayonConcentre extends AtkRayon{
    private Entite cible;

    public AtkRayonConcentre(Cellule cellule, int degats){
        super(cellule , degats);
    }


    public void setCible(Entite cible) {
        this.cible = cible;
    }


    public void reconnaissanceCible(){
        if (!getCellule().getReconnaissance().getCibles().isEmpty()) {
            if (getCellule().getReconnaissance().getCibles().get(0) == cible) {
                setDegats(getDegats() + 1);
            } else {
                setCible(getCellule().getReconnaissance().getCibles().get(0));
                setDegats(1);
            }
        }
    }

    @Override
    public void attaqueCibles() {
        reconnaissanceCible();
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }
}
