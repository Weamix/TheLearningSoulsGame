package lsg.graphics.panes;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lsg.graphics.widgets.texts.GameLabel;

public class CreationPane extends VBox {
    private GameLabel gameLabel;
    private TextField nameInput;

    public CreationPane() {
        nameInput = new TextField();
        gameLabel = new GameLabel("Player name");
        getChildren().add(gameLabel);
        getChildren().add(nameInput);
        setAlignment(Pos.CENTER);
    }

    public TextField getNameInput() {
        return nameInput;
    }


    public void fadeIn (EventHandler<ActionEvent> finishedHandler) {
        FadeTransition ft = new FadeTransition();

        ft.setDuration(Duration.millis(5000));
        ft.setNode(this);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1000);
        ft.setAutoReverse(true);
        ft.play();
    }
}
