package lsg.graphics.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import lsg.graphics.widgets.characters.statbars.StatBar;
import lsg.graphics.widgets.skills.SkillBar;

public class HUDPane extends BorderPane {
    private MessagePane messagePane;
    private StatBar heroStatBar;
    private StatBar zombieStatBar;
    private SkillBar skillBar;

    public HUDPane(){
        buildCenter();
        buildTop();
        buildBottom();
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

    private void buildBottom(){
        skillBar = new SkillBar();
        setBottom(skillBar);
        setMargin(skillBar, new Insets(0, 0, 20, 0));

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

    public SkillBar getSkillBar() { return skillBar; }
}
