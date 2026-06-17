package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Virus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecSimpleTest {
    Environnement env = new Environnement("Niveau 1");

    /* Voici la carte utilise pour essayer la Reconnaissance les 1 represente un mur.
    1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    1 0 1 0 0 0 0 0 1 0 0 0 0 1 0 0 0 1 0 1 1 1 0 0 0 0 0 0 0 1
    0 0 0 0 0 1 1 0 0 0 1 0 0 1 0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1
    1 1 0 1 0 0 1 1 0 0 0 1 0 0 0 0 0 1 0 1 0 0 1 0 0 1 1 0 0 1
    1 0 0 1 1 0 1 0 0 1 0 1 0 0 0 1 0 0 0 1 1 0 0 0 0 0 0 0 0 1
    1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 1 1 0 1 0 0 1
    1 0 0 1 1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1 0 1
    1 0 0 0 0 0 0 1 0 0 1 0 1 0 0 1 0 0 1 0 1 0 0 0 0 0 0 0 0 0
    1 1 0 1 0 1 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 1 0 0 1 0 1
    1 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 1 1 0 0 0 1 1 0 0 1 0 0 1
    1 0 0 1 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1 0 0 0 1
    1 0 1 0 1 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 1 0 0 1 0 1 0 1
    1 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 1 0 1 0 0 1 0 1 0 0 0 1
    1 1 0 0 0 0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1
    1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
     */

    @Test
    void aPortee(){
        Reconnaissance recSimple = new RecSimple(env, 2,2,3,1);
        assertTrue(recSimple.aPortee(new Virus(env,2,3)),"La cible est a portée");
        assertFalse(recSimple.aPortee(new Virus(env,2,9)), "La maladie est hors de portée");
        assertTrue(recSimple.aPortee(new Virus(env,-1,2)), "La maladie est hors de la carte");
        assertTrue(recSimple.aPortee(new Virus(env,1,2)), "La maladie est dans un Mur");

        Reconnaissance recSimpleHorsCarte = new RecSimple(env, -1,1,3,1);
        assertTrue(recSimpleHorsCarte.aPortee(new Virus(env,1,1)), "La reconnaissance est hors de la carte");
    }

    @Test
    void estValide(){
        Reconnaissance recSimple = new RecSimple(env, 2,2,3,1);
        assertTrue(recSimple.estValide(new Virus(env,2,3)),"La Cellule voit la maladie");
        assertFalse(recSimple.estValide(new Virus(env,10,10)),"Maladie hors de portée");
        assertFalse(recSimple.estValide(new Virus(env,-1,2)),"Maladie hors de la carte");
        assertFalse(recSimple.estValide(new Virus(env,1,2)), "La maladie est dans un Mur");

        Reconnaissance recSimple2 = new RecSimple(env,1,3,3,1);
        assertFalse(recSimple2.estValide(new Virus(env,1,1)), "Un mur separe la maladie et reconnaissance");

        Reconnaissance recSimpleHorsCarte = new RecSimple(env,-1,1,3,1);
        assertFalse(recSimpleHorsCarte.estValide(new Virus(env,1,1)), "Reconnaissance hors de la carte");
    }

    @Test
    void aAuMoinsUneCible(){
        Reconnaissance recSimple = new RecSimple(env,2,2,3,1);
        env.ajouterMaladie(new Virus(env,2,3));
        recSimple.actualiser();
        assertTrue(recSimple.aAuMoinsUneCible(), "Il y a exactement une cible valide");

        env.getMaladies().clear();
        recSimple.actualiser();
        assertFalse(recSimple.aAuMoinsUneCible(),"Il y a pas de cibles");

        env.ajouterMaladie(new Virus(env,2,3));
        env.ajouterMaladie(new Virus(env,2,1));
        recSimple.actualiser();
        assertTrue(recSimple.aAuMoinsUneCible(),"Plusieurs cibles valides");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,-1,1));
        Reconnaissance recSimple2 = new RecSimple(env,1,1,3,1);
        recSimple2.actualiser();
        assertFalse(recSimple2.aAuMoinsUneCible(),"La cible est hors Carte");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,1,2));
        recSimple2.actualiser();
        assertFalse(recSimple2.valide(),"La cible est dans un mur");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,1,3));
        recSimple2.actualiser();
        assertFalse(recSimple2.aAuMoinsUneCible(),"La cible est derriere un mur");

        Reconnaissance recSimple3 = new RecSimple(env,-1,1,3,1);
        recSimple3.actualiser();
        assertFalse(recSimple3.aAuMoinsUneCible(),"La reconnaissance est en dehors de la carte");
    }

    @Test
    void aAssezDeCibles(){
        Reconnaissance recSimple = new RecSimple(env,2,2,3,1);
        env.ajouterMaladie(new Virus(env,2,3));
        recSimple.actualiser();
        assertTrue(recSimple.aAssezDeCibles(), "Il y a exactement une cible valide");

        env.getMaladies().clear();
        recSimple.actualiser();
        assertFalse(recSimple.aAssezDeCibles(),"Il y a pas de cibles");

        env.ajouterMaladie(new Virus(env,2,3));
        env.ajouterMaladie(new Virus(env,2,1));
        recSimple.actualiser();
        assertTrue(recSimple.aAssezDeCibles(),"Plusieurs cibles valides");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,-1,1));
        Reconnaissance recSimple2 = new RecSimple(env,1,1,3,1);
        recSimple2.actualiser();
        assertFalse(recSimple2.aAssezDeCibles(),"La cible est hors carte");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,1,2));
        recSimple2.actualiser();
        assertFalse(recSimple2.valide(),"La cible est dans un mur");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,1,3));
        recSimple2.actualiser();
        assertFalse(recSimple2.aAssezDeCibles(),"La cible est derriere un mur");

        Reconnaissance recSimple3 = new RecSimple(env,-1,1,3,1);
        recSimple3.actualiser();
        assertFalse(recSimple3.aAssezDeCibles(),"La reconnaissance est en dehors de la carte");
    }


    @Test
    void valide(){
        Reconnaissance recSimple = new RecSimple(env,2,2,3,1);
        env.ajouterMaladie(new Virus(env,2,3));
        recSimple.actualiser();
        assertTrue(recSimple.valide(), "Il y a exactement une cible valide");

        env.getMaladies().clear();
        recSimple.actualiser();
        assertFalse(recSimple.valide(),"Il y a pas de cibles");

        env.ajouterMaladie(new Virus(env,2,3));
        env.ajouterMaladie(new Virus(env,2,1));
        recSimple.actualiser();
        assertTrue(recSimple.valide(),"Plusieurs cibles valides");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,-1,1));
        Reconnaissance recSimple2 = new RecSimple(env,1,1,3,1);
        recSimple2.actualiser();
        assertFalse(recSimple2.valide(),"La cible est hors carte");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,1,2));
        recSimple2.actualiser();
        assertFalse(recSimple2.valide(),"La cible est dans un mur");

        env.getMaladies().clear();
        env.ajouterMaladie(new Virus(env,1,3));
        recSimple2.actualiser();
        assertFalse(recSimple2.valide(),"La cible est derriere un mur");

        Reconnaissance recSimple3 = new RecSimple(env,-1,1,3,1);
        recSimple3.actualiser();
        assertFalse(recSimple3.valide(),"La reconnaissance est en dehors de la carte");
    }


}