package view;

import javax.swing.*;

public class LoadButton extends JButton {
    private String methodName;

    public LoadButton(String text, String methodName) {
        super(text);
        this.methodName = methodName;
    }
}
