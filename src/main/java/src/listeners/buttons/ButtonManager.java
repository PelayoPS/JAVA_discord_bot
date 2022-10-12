package src.listeners.buttons;

import src.commands.general.help.HelpButtonListener;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {

    private List<ButtonListener> buttonListeners = new ArrayList<>();

    public ButtonManager() {
        registerButtonListener();
    }

    public void registerButtonListener() {
        //Help menu buttons
        buttonListeners.add(new HelpButtonListener());

    }

    public List<ButtonListener> getButtonListeners() {
        return buttonListeners;
    }

}
