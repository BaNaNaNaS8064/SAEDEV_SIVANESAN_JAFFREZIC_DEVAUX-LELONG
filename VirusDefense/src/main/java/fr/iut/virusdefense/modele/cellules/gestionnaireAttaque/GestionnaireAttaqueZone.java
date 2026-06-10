package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueZone extends GestionnaireAttaque {
    private final double rayonZone;

    public GestionnaireAttaqueZone(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles, double rayonZone){
        super(environnement, ligne, colonne, degats, cibles);
        this.rayonZone = rayonZone;
    }

    @Override
    public final void attaqueCibles(){
        getEnvironnement().ajouterZone(new Zone(getEnvironnement(), getLigne(), getColonne(), getCibles() , getDegats(), 10 , getAlterations(), rayonZone));
    }
}
