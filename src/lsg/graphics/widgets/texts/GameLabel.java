package lsg.graphics.widgets.texts;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class GameLabel extends Label {
    public GameLabel() {
        css();
    }

    public GameLabel(String text) {
        super(text);
        css();
    }

    public GameLabel(String text, Node graphic) {
        super(text, graphic);
        css();
    }

    private void css(){
        this.getStyleClass().addAll("game-font");
        this.getStyleClass().addAll("game-font-fx");
    }
}
