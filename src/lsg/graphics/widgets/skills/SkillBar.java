package lsg.graphics.widgets.skills;

import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class SkillBar extends HBox {
    private static final LinkedHashMap<KeyCode, String> DEFAULT_BINDING = new LinkedHashMap<>();

    static {
        DEFAULT_BINDING.put(KeyCode.DIGIT1, "&");
        DEFAULT_BINDING.put(KeyCode.DIGIT2, "é");
        DEFAULT_BINDING.put(KeyCode.DIGIT3, "\"");
        DEFAULT_BINDING.put(KeyCode.DIGIT4, "'");
        DEFAULT_BINDING.put(KeyCode.DIGIT5, "(");
    }

    private SkillTrigger[] triggers;

    public SkillBar(){
        setSpacing(10);
        setPrefHeight(110);
        setAlignment(Pos.CENTER);
    }

    private void init(){
        triggers = new SkillTrigger[DEFAULT_BINDING.size()];
        for (Map.Entry<KeyCode,String> bind : DEFAULT_BINDING.entrySet() ) {
            int i = 0;
            triggers[i] = new SkillTrigger(bind.getKey(), bind.getValue(), null, null);
            getChildren().add(triggers[i]);
            i++;
        }
    }

    public SkillTrigger getTrigger(int slot){
        if(slot >= triggers.length && slot > 0) {
            return triggers[slot];
        }
        return null;
    }

    public void process(KeyCode code) {
        if (isDisabled()) return;
            for (SkillTrigger skillTrigger : triggers) {
                if (skillTrigger.getKeyCode() == code) {
                    skillTrigger = getTrigger(new LinkedList<KeyCode>(DEFAULT_BINDING.keySet()).indexOf(code));
                    skillTrigger.trigger();
                }
            }
    }
}
