package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.entitesgeneriques.ZoneSimple;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkZone extends Attaque{
    private final double rayonZone;

    public AtkZone(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles, double rayonZone){
        super(environnement, ligne, colonne, degats, cibles);
        this.rayonZone = rayonZone;
    }

    @Override
    public final void attaqueCibles(){
        getEnvironnement().ajouterZone(new ZoneSimple(getEnvironnement(), getLigne(), getColonne(), getCibles() , getDegats(), 10 , getAlterations(), rayonZone));
    }
}
