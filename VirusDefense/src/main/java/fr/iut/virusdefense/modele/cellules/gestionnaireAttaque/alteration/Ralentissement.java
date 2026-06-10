package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.alteration;

public class Ralentissement extends Alteration{
    private final double coefRalentissement;

    public Ralentissement(int duree, double coefRalentissement){
        super(duree);
        this.coefRalentissement = coefRalentissement;
    }

    @Override
    public void affecter() {
        getMaladie().ralentir(coefRalentissement);
    }
}
