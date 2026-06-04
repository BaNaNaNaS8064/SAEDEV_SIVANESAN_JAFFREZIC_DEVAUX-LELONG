package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkZone extends Attaque{
    private final double rayonZone;
    private final int degats;

    public AtkZone(Environnement environnement, double ligne, double colonne, int degats, ArrayList<Maladie> cibles, double rayonZone){
        super(environnement, ligne, colonne, cibles);
        this.degats = degats;
        this.rayonZone = rayonZone;
    }

    @Override
    public final void attaqueCibles(){
        getEnvironnement().ajouterZone(new Zone(getEnvironnement(), getLigne(), getColonne(), getCibles() , degats, 10 , getAlterations(), rayonZone));
    }
}
