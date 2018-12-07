package view;

import javax.swing.*;

//TODO: needed?
class ButtonForDrawing extends JToggleButton {
    private String methodName;

    String getMethodName() {
        return methodName;
    }

    ButtonForDrawing(String text, String methodName) {
        super(text);
        this.methodName = methodName;
    }
}
