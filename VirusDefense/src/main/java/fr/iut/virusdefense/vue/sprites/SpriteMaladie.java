package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteMaladie extends ImageView{


    private final Maladie m;

    public SpriteMaladie(Maladie m){
        super();
        this.m = m;
        creerSprite();
    }

    private void creerSprite(){
        setImage(new Image(String.valueOf(Main.class.getResource("images/maladies/" +
                switch (m.getClass().getSimpleName()){
                    case "Virus" -> "Vi";
                    case "Parasite" -> "Pa";
                    default -> "BB";
                } + ".png"))));

        translateXProperty().bind(m.colonneProperty().multiply(32).subtract(8));
        translateYProperty().bind(m.ligneProperty().multiply(32).subtract(8));
        setId(m.getId());
    }

    public void retirerSprite(){

    }

}
