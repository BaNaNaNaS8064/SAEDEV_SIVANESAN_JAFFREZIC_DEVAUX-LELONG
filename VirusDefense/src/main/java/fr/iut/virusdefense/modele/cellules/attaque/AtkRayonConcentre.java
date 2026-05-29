package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

public class AtkRayonConcentre extends AtkRayon{
    private Entite cible;
    private int delai = 0;

    public AtkRayonConcentre(Cellule cellule, int degats){
        super(cellule , degats);
    }


    public void setCible(Entite cible) {
        this.cible = cible;
    }


    public void reconnaissanceCible(){
        if (!getCellule().getReconnaissance().getCibles().isEmpty()) {
            if (getCellule().getReconnaissance().getCibles().get(0) == cible) {
                if (delai%150==0)
                    setDegats(getDegats() + 1);
            } else {
                setCible(getCellule().getReconnaissance().getCibles().get(0));
                setDegats(1);
            }
        }
        delai++;
    }

    @Override
    public void attaqueCibles() {
        reconnaissanceCible();
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }
}
