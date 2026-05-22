package fr.iut.virusdefense.modele;

public class Rayon extends Entite{
    public double ligne2;
    public double colonne2;

    private int age;

    public Rayon(Environnement environnement, double ligne, double colonne, double ligne2, double colonne2){
        super(environnement, ligne, colonne);
        this.ligne2 = ligne2;
        this.colonne2 = colonne2;
        age = 0;
    }

    public int getAge() {
        return age;
    }

    public double getLigne2() {
        return ligne2;
    }

    public double getColonne2() {
        return colonne2;
    }

    public boolean peutRelierExtremitees(boolean ignorerCellules){
        double positionLigne, positionColonne = getColonne();
        int nombreDePoints = 100;
        double distColonne = getColonne2() - getColonne();
        double pente = (getLigne2() - getLigne()) / (distColonne);
        double ordoneeOrigine = getLigne() - pente * getColonne();
        boolean bloque = false;
        int i=0;

        while (!bloque && i<nombreDePoints){
            positionColonne += (double)1 / nombreDePoints * distColonne;
            positionLigne = (pente * positionColonne + ordoneeOrigine);
            if (!getEnvironnement().getCarte().peutVoirAuTravers((int)positionLigne, (int)positionColonne, ignorerCellules)
                    && ((int)positionLigne != (int)getLigne() || (int)positionColonne != (int)getColonne()))
                bloque = true;

            i++;
        }

        return !bloque;
    }

    @Override
    public void agir(){
        age++;
    }
}
