package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

/**
 * Le Niveau se charge de ce qui touche aux vagues et aux apparitions <br>
 * Ce n'est pas son rôle de stocker les générateurs (rôle de {@code Carte})
 * ni de faire apparaître les maladies (rôle de {@code Generateur})
 */
public class Niveau {

    /// L'environnement dans lequel tout se passe
    private final Environnement environnement;

    /// L'indice de la vague actuelle
    private final IntegerProperty numVagueProperty;
    /// La liste des vagues
    private ArrayList<Vague> vagues;

    /// Le délai entre chaque vague
    private int delaiEntreVagues;

    /// le nombre restant de tours avant la prochain vague
    int delai;

    /**
     * Créé un nouveau niveau dans {@code environnement} <br>
     * Le niveau sera automatiquement rempli de vagues et démarrera la première vague
     * @param environnement l'environnement dans lequel le niveau sera
     */
    public Niveau(Environnement environnement){
        this.environnement = environnement;
        delaiEntreVagues = 600;
        numVagueProperty = new SimpleIntegerProperty(-1);
        initVagues();
        passerProchaineVague();
    }

    public final int getNumVague(){
        return numVagueProperty.getValue();
    }

    public final IntegerProperty numVagueProperty(){
        return numVagueProperty;
    }

    public final void setNumVague(int numVague){
        numVagueProperty.setValue(numVague);
    }

    /**
     * Créé toutes les vagues du niveau de façon semi-aléatoire <br>
     * Chaque vague aura autant de listes d'apparition
     * que le nombre de générateurs dans la carte de {@code environnnement}
     */
    public void initVagues(){
        vagues = new ArrayList<>();
        for (int indVague=0; indVague<100; indVague++){
            vagues.add(new Vague());
            for (int indListeA=0; indListeA<environnement.getCarte().getGenerateurs().size(); indListeA++) {
                vagues.get(indVague).ajouter(new ListeApparition());
                for (int indEnnemi = 0; indEnnemi < 4 * (indVague + 1); indEnnemi++)
                    vagues.get(indVague).getListeApparitions().get(indListeA).ajouter(CodeMaladie.codeAleatoire(), (int) ((Math.random() * 90 + 30)) / (2 * (indVague + 1)));
            }
        }
        vagues.get(vagues.size() - 1).getListeApparitions().get(0).ajouter(CodeMaladie.TUMEUR, 0);
    }

    /**
     * Démmare la prochaine vague,
     * c'est à dire met à jour les listes d'apparitions des générateurs
     * de la carte de {@code environnement}
     */
    public void passerProchaineVague(){
        numVagueProperty.setValue(getNumVague() + 1);
        environnement.getJoueur().ajouterPc(50);
        for (int i=0; i<environnement.getCarte().getGenerateurs().size(); i++)
            environnement.getCarte().getGenerateurs().get(i).setListe(vagues.get(getNumVague()).getListeApparitions().get(i));
         delai = Integer.MAX_VALUE;
    }

    /**
     * Retourne vrai s'il reste des vagues, faux sinon
     * @return vrai s'il reste des vagues, faux sinon
     */
    public boolean resteVague(){
        return getNumVague() < vagues.size() - 1;
    }

    public void update(){
        if (!estTermine()) {
            if (--delai <= 0)
                passerProchaineVague();

            if (vagues.get(getNumVague()).estTerminee() && delai > delaiEntreVagues)
                delai = delaiEntreVagues;
        }
    }

    /**
     * Retourne vrai si le niveau est terminé, faux sinon <br>
     * Le niveau est terminé si il ne reste plus de vague
     * et que la dernière vague est terminée
     * @return Retourne vrai si le niveau est terminé, faux sinon
     */
    public boolean estTermine(){
        return !resteVague() && vagues.get(vagues.size() - 1).estTerminee();
    }

    /**
     * Retourne le nombre de vagues total du niveau
     * @return le nombre de vagues total du niveau
     */
    public int nombreDeVagues(){
        return vagues.size();
    }
}
