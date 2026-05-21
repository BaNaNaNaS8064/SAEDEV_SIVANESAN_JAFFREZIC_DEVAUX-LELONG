package fr.iut.virusdefense.modele;

public class Rayon extends Entite{
    public double ligneArrivee;
    public double colonneArrivee;

    private int age;

    public Rayon(Environnement environnement, double ligneDepart, double colonneDepart, double ligneArrivee, double colonneArrivee){
        super(environnement, ligneDepart, colonneDepart);
        this.ligneArrivee = ligneArrivee;
        this.colonneArrivee = colonneArrivee;
        age = 0;
    }

    public int getAge() {
        return age;
    }

    public double getLigneArrivee() {
        return ligneArrivee;
    }

    public double getColonneArrivee() {
        return colonneArrivee;
    }

    public boolean departVoitArrivee(int ligneExclure, int colonneExclure){
        double positionLigne, positionColonne = getColonne();
        int nombreDePoints = 100;
        double distColonne = getColonneArrivee() - getColonne();
        double pente = (getLigneArrivee() - getLigne()) / (distColonne);
        double ordoneeOrigine = getLigne() - pente * getColonne();
        boolean bloque = false;
        int i=0;

        while (!bloque && i<nombreDePoints){
            positionColonne += (double)1 / nombreDePoints * distColonne;
            positionLigne = (pente * positionColonne + ordoneeOrigine);
            if (!getEnvironnement().getCarte().peutMarcher((int)positionLigne, (int)positionColonne) && ((int)positionLigne != ligneExclure || (int)positionColonne != colonneExclure))
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
