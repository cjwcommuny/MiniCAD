package view;

import javax.swing.*;

public class SaveButton extends JButton {
    private String methodName;

    public SaveButton(String text, String methodName) {
        super(text);
        this.methodName = methodName;
    }
}
