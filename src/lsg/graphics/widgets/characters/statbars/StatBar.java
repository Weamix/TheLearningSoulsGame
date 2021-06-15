package lsg.graphics.widgets.characters.statbars;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lsg.graphics.ImageFactory;
import lsg.graphics.widgets.texts.GameLabel;

public class StatBar extends BorderPane {

    private ImageView avatar;
    private GameLabel name;
    private ProgressBar lifeBar;
    private ProgressBar stamBar;

    public StatBar() {
        setPrefWidth(350);
        setPrefHeight(100);
        //this.setStyle("-fx-border-color: red");

        avatar = new ImageView();
        avatar.setImage(ImageFactory.getSprites(ImageFactory.SPRITES_ID.HERO_HEAD)[0]);
        avatar.setPreserveRatio(true);
        avatar.setFitHeight(100);
        setLeft(avatar);

        name = new GameLabel("name");
        name.setStyle("-fx-font-size: 33px");

        lifeBar = new ProgressBar();
        lifeBar.setMaxWidth(Double.MAX_VALUE);
        lifeBar.setStyle("-fx-accent: red");

        stamBar = new ProgressBar();
        stamBar.setMaxWidth(Double.MAX_VALUE-25);
        stamBar.setStyle("-fx-accent: greenyellow");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(name, lifeBar, stamBar);
        setCenter(vbox);
    }

    public void flip(){
        setScaleX(-getScaleX());
        name.setScaleX(-name.getScaleX());
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public GameLabel getName() {
        return name;
    }

    public ProgressBar getLifeBar() {
        return lifeBar;
    }

    public ProgressBar getStamBar() {
        return stamBar;
    }
}
