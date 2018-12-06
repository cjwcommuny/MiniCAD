package view;

import javax.swing.*;

//TODO: needed?
public class ButtonForDrawing extends JButton {
    private boolean isDone = false;
    private String methodName;

    public boolean isDone() {
        return isDone;
    }

    public String getMethodName() {
        return methodName;
    }

    public ButtonForDrawing(String text, String methodName) {
        super(text);
        this.methodName = methodName;
    }
}
