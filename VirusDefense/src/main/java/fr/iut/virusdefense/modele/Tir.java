package fr.iut.virusdefense.modele;

public class Tir extends Entite{
    public double ligneArrivee;
    public double colonneArrivee;

    private int age;

    public Tir(Environnement environnement, double ligneDepart, double colonneDepart, double ligneArrivee, double colonneArrivee){
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


    @Override
    public void agir(){
        age++;
    }
}
