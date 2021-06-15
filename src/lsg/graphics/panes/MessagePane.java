package lsg.graphics.panes;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lsg.graphics.widgets.texts.GameLabel;

public class MessagePane extends VBox {
    public void showMessage(String msg){
        GameLabel gameLabel = new GameLabel(msg);
        getChildren().add(gameLabel);
        setAlignment(Pos.CENTER);

        TranslateTransition tt = new TranslateTransition(Duration.millis(3000));
        tt.setToY(-200);

        FadeTransition ft = new FadeTransition(Duration.millis(3000));
        ft.setByValue(1);
        ft.setToValue(0);
        ft.play();

        ParallelTransition pt = new ParallelTransition(tt,ft);
        pt.setNode(gameLabel);
        pt.setCycleCount(1);

        pt.setOnFinished(event -> getChildren().remove(gameLabel));

        pt.play();
    }
}
