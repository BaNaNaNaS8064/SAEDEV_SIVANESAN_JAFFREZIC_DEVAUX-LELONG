package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

public class AtkRayonConcentre extends AtkRayon{
    private Entite cible;
    private int delai = 0;
    private int delaiAugmentationAttaque;
    private double degatBase;

    public AtkRayonConcentre(Cellule cellule, double degats, int delaiAugmentation){
        super(cellule , degats);
        this.delaiAugmentationAttaque = delaiAugmentation;
        degatBase=degats;
    }

    public int getDelaiAugmentationAttaque() {
        return delaiAugmentationAttaque;
    }

    public double getDegatBase() {
        return degatBase;
    }

    public void setCible(Entite cible) {
        this.cible = cible;
    }

    public void setDelaiAugmentationAttaque(int delaiAugmentationAttaque) {
        this.delaiAugmentationAttaque = delaiAugmentationAttaque;
    }

    public void setDegatBase(double degatBase) {
        this.degatBase = degatBase;
    }

    public void reconnaissanceCible(){
        if (!getCellule().getReconnaissance().getCibles().isEmpty()) {
            if (getCellule().getReconnaissance().getCibles().get(0) == cible) {
                if (delai% delaiAugmentationAttaque ==0)
                    setDegats(getDegats() + 1);
            } else {
                setCible(getCellule().getReconnaissance().getCibles().get(0));
                setDegats(degatBase);
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
