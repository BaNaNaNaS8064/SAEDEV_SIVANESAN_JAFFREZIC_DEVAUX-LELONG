package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.Attaque;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

public abstract class Cellule extends Entite {
    private Reconnaissance reconnaissance ;
    private Attaque attaque;

    private final int frequenceAttaque;
    private int delai;

    private int niveau;
    private final int cout;
    private int coutAmelioration;

    public Cellule(Environnement environnement, int ligne, int colonne, int frequenceAttaque, int cout, int coutAmelioration){
        super(environnement, ligne, colonne);

        this.frequenceAttaque = frequenceAttaque;
        this.delai = frequenceAttaque;

        this.niveau = 1;

        this.cout = cout;
        this.coutAmelioration = coutAmelioration;
    }

    public abstract void initRec();

    public abstract void initAttaque();

    public Reconnaissance getReconnaissance(){
        return reconnaissance;
    }

    public void setReconnaissance(Reconnaissance reconnaissance){
        this.reconnaissance = reconnaissance;
    }

    public void setAttaque(Attaque attaque){
        this.attaque = attaque;
    }

    public int getCout() {
        return cout;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getFrequenceAttaque() {
        return frequenceAttaque;
    }

    public Attaque getAttaque() {
        return attaque;
    }

    public int getCoutAmelioration() {
        return coutAmelioration;
    }

    public void setCoutAmelioration(int coutAmelioration) {
        this.coutAmelioration = coutAmelioration;
    }

    abstract public String nomCellule();


    public void niveauSuperieur(){
        if (getEnvironnement().getJoueur().getPc()> getCoutAmelioration()) {
            niveau++;
            this.amelioration();
        }
    }

    public void amelioration(){
        if (niveau==2){
            getEnvironnement().getJoueur().retirerPc(getCoutAmelioration());
            niveau2();
        }
        else if (niveau==3){
            getEnvironnement().getJoueur().retirerPc(getCoutAmelioration());
            niveau3();
        }
    }

    abstract public void niveau2();

    abstract public void niveau3();


    @Override
    public void agir(){
        if (--delai <= 0){
            if (!reconnaissance.ciblesValides())
                reconnaissance.changerCibles();

            if (reconnaissance.aAuMoinsUneCible()) {
                attaque.attaqueCibles();
                delai = frequenceAttaque;
            }
            else {
                delai = frequenceAttaque / 10;
            }
        }
    }
}
