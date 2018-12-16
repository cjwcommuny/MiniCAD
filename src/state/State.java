package state;


import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Model;
import shape.Shape;
import shape.Text;
import state.drawing_ellipse_state.DrawFirstPointOfEllipse;
import state.drawing_filled_ellipse.DrawFirstPointOfFilledEllipse;
import state.drawing_filled_rectangle.DrawFirstPointOfFilledRectangle;
import state.drawing_line_state.DrawFirstPointOfLine;
import state.drawing_multipleline_state.DrawInitialPointOfMultipleLine;
import state.drawing_polygon_state.DrawInitialPointOfPolygon;
import state.drawing_rectangle_state.DrawFirstPointOfRectangle;
import state.drawing_text_state.FinishInputTextState;
import state.drawing_text_state.ReadyToInputTextState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.sun.glass.events.KeyEvent.VK_BACKSPACE;
import static java.awt.event.KeyEvent.*;

abstract public class State {
    private static Map<Integer, Runnable> keyPressedMap = new HashMap<>();

    static
    {
        initializeKeyPressedMap();
    }

    abstract public State mouseLeftClick(MouseEvent e);
    abstract public State mouseRightClick(MouseEvent e);

    public State mouseMove(MouseEvent e) {
        return this;
    }

    public State chooseModeButtonPressed() {
        Model.setCurrentShape(null);
        return Idle.getInstance();
    }

    public State lineButtonPressed() {
        Model.setCurrentShape(null);
        return DrawFirstPointOfLine.getInstance();
    }

    public State rectangleButtonPressed() {
        Model.setCurrentShape(null);
        return DrawFirstPointOfRectangle.getInstance();
    }

    public State ellipseButtonPressed() {
        Model.setCurrentShape(null);
        return DrawFirstPointOfEllipse.getInstance();
    }

    public State filledRectangleButtonPressed() {
        Model.setCurrentShape(null);
        return DrawFirstPointOfFilledRectangle.getInstance();
    }

    public State filledEllipseButtonPressed() {
        Model.setCurrentShape(null);
        return DrawFirstPointOfFilledEllipse.getInstance();
    }


    public State multipleLineButtonPressed() {
        Model.setCurrentShape(null);
        return DrawInitialPointOfMultipleLine.getInstance();
    }

    public State polygonButtonPressed() {
        Model.setCurrentShape(null);
        return DrawInitialPointOfPolygon.getInstance();
    }

    public State textButtonPressed() {
        Model.setCurrentShape(null);
        return ReadyToInputTextState.getInstance();
    }

    public State keyButtonReleased(int keyCode) {
        if (isTextInput(keyCode) && Model.getCurrentShape().getClass() == Text.class) {
            changeText(keyCode);
        } else {
            keyPressedMap.getOrDefault(keyCode, () -> {}).run();
        }
        Model.shapeListChanged();
        return this;
    }

    private static void changeText(int keyCode) {
        String keyString = KeyEvent.getKeyText(keyCode);
        Text textShape = (Text) Model.getCurrentShape();
        String originalText = textShape.getText();
        if (keyCode == VK_BACKSPACE && originalText.length() >= 1) {
            textShape.setText(originalText.substring(0, originalText.length() - 1));
        } else if (keyCode == VK_SPACE) {
            textShape.setText(originalText + " ");
        } else if ((keyCode >= VK_0 && keyCode <= VK_9) || (keyCode >= VK_A && keyCode <= VK_Z)) {
            textShape.setText(originalText + keyString.toLowerCase());
        } else if (keyCode == VK_F7) {
            textShape.setFontStyle("Times");
            Model.shapeListChanged();
        } else if (keyCode == VK_F8) {
            textShape.setFontStyle("Menlo");
            Model.shapeListChanged();
        }
    }

    private boolean isTextInput(int keyCode) {
        boolean isChar = keyCode >= VK_A && keyCode <= VK_Z;
        boolean isNumber = keyCode >= VK_0 && keyCode <= VK_9;
        boolean isBackspaceOrSpace = keyCode == VK_BACK_SPACE || keyCode == VK_SPACE;
        return isChar || isNumber || isBackspaceOrSpace;
    }

    private static void initializeKeyPressedMap() {
        keyPressedMap.put(VK_F1, () -> Model.incrementLineWidthOfCurrentShape());
        keyPressedMap.put(VK_F2, () -> Model.decrementLineWidthOfCurrentShape());
        keyPressedMap.put(VK_F3, () -> Model.setColor(Shape.NORMAL_COLOR));
        keyPressedMap.put(VK_F4, () -> Model.setColor(Color.RED));
        keyPressedMap.put(VK_F5, () -> Model.setColor(Color.BLUE));
        keyPressedMap.put(VK_F6, () -> Model.setColor(Color.YELLOW));
        keyPressedMap.put(VK_F7, () -> Model.setTextFontStyle("Times"));
        keyPressedMap.put(VK_F8, () -> Model.setTextFontStyle("Menlo"));
        keyPressedMap.put(VK_BACK_SPACE, () -> Model.removeShape());
        keyPressedMap.put(VK_DELETE, () -> Model.removeShape());
    }

    public State mouseDragged(MouseEvent e, Point direction) {
        return this;
    }

    public State mouseWheelMoved(int notches) {
        final double unitRate = 0.05;
        Shape shape = Model.getCurrentShape();
        if (shape != null) {
            shape.changeSize(unitRate * notches);
            Model.shapeListChanged();
        }
        return this;
    }

    public void invoke(String methodName) {
        try {
            Method method = getClass().getMethod(methodName);
            State newState = (State) method.invoke(this);
            Model.setCurrentState(newState);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    protected void addShape(Shape shape) {
        Model.addShape(shape);
        Model.shapeListChanged();
    }
}
