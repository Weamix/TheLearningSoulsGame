package lsg.graphics.widgets.skills;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import lsg.graphics.CSSFactory;

//TODO début page 4

public class SkillTrigger extends AnchorPane {

    private ImageView view;
    private Label text;
    private KeyCode keyCode;
    private SkillAction action;

    public SkillTrigger(KeyCode keyCode, String text, Image image, SkillAction action) {
        setKeyCode(keyCode);
        setText(new Label(text));
        setImage(image);
        setAction(action);
        buildUI();
        addListeners();
    }

    private void buildUI() {
        getStylesheets().add(CSSFactory.getStyleSheet("SkillTrigger.css"));
        getStyleClass().addAll("skill");
        view.setFitHeight(50);
        view.setFitWidth(50);
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        getChildren().add(view);
        AnchorPane.setTopAnchor(text, 0.0);
        AnchorPane.setRightAnchor(text, 0.0);
        AnchorPane.setBottomAnchor(text, 0.0);
        AnchorPane.setLeftAnchor(text, 0.0);
        getChildren().add(text);

    }

    public void trigger(){
        if(!isDisable() || action != null){
            action.execute();
        }
    }

    private void addListeners(){
        setOnMouseClicked(event -> trigger());
    }

    public Label getText() { return text; }
    public void setText(Label text) { this.text = text; }

    public KeyCode getKeyCode() { return keyCode; }
    public void setKeyCode(KeyCode keyCode) { this.keyCode = keyCode; }

    public SkillAction getAction() { return action; }
    public void setAction(SkillAction action) { this.action = action; }

    public Image getImage() { return view.getImage(); }
    public void setImage(Image image) { view.setImage(image); }
}
