package state;


import model.Model;
import shape.Shape;
import state.drawing_ellipse_state.DrawFirstPointOfEllipse;
import state.drawing_filled_ellipse.DrawFirstPointOfFilledEllipse;
import state.drawing_filled_rectangle.DrawFirstPointOfFilledRectangle;
import state.drawing_line_state.DrawFirstPointOfLine;
import state.drawing_multipleline_state.DrawInitialPointOfMultipleLine;
import state.drawing_polygon_state.DrawInitialPointOfPolygon;
import state.drawing_rectangle_state.DrawFirstPointOfRectangle;
import state.drawing_text_state.ReadyToInputTextState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
        return Idle.getInstance();
    }

    public State lineButtonPressed() {
        return DrawFirstPointOfLine.getInstance();
    }

    public State rectangleButtonPressed() {
        return DrawFirstPointOfRectangle.getInstance();
    }

    public State ellipseButtonPressed() {
        return DrawFirstPointOfEllipse.getInstance();
    }

    public State filledRectangleButtonPressed() {
        return DrawFirstPointOfFilledRectangle.getInstance();
    }

    public State filledEllipseButtonPressed() {
        return DrawFirstPointOfFilledEllipse.getInstance();
    }


    public State multipleLineButtonPressed() {
        return DrawInitialPointOfMultipleLine.getInstance();
    }

    public State polygonButtonPressed() {
        return DrawInitialPointOfPolygon.getInstance();
    }

    public State textButtonPressed() {
        return ReadyToInputTextState.getInstance();
    }

    public State keyButtonReleased(int keyCode) {
        keyPressedMap.getOrDefault(keyCode, () -> {}).run();
        Model.shapeListChanged();
        return this;
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
