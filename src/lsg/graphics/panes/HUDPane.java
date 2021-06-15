package lsg.graphics.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import lsg.graphics.widgets.characters.statbars.StatBar;

public class HUDPane extends BorderPane {
    private MessagePane messagePane;
    private StatBar heroStatBar;
    private StatBar zombieStatBar;

    public HUDPane(){
        buildCenter();
        buildTop();
    }

    private void buildCenter() {
        messagePane = new MessagePane();
        setCenter(messagePane);
    }

    private void buildTop(){
        BorderPane borderPane = new BorderPane();
        setTop(borderPane);

        heroStatBar = new StatBar();
        zombieStatBar = new StatBar();

        borderPane.setLeft(heroStatBar);
        borderPane.setRight(zombieStatBar);

        zombieStatBar.flip();

        setPadding(new Insets(50,20,0,20));

    }

    public MessagePane getMessagePane() {
        return messagePane;
    }

    public StatBar getHeroStatBar() {
        return heroStatBar;
    }

    public StatBar getZombieStatBar() {
        return zombieStatBar;
    }
}
