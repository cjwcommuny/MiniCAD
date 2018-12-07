package view;

import model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    static class DrawingButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ButtonForDrawing button = (ButtonForDrawing) e.getSource();
            String methodName = button.getMethodName();
            Model.getCurrentState().invoke(methodName);
        }
    }
}
