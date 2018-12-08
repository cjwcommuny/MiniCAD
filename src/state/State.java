package state;


import model.Model;
import shape.Shape;
import state.drawing_ellipse_state.ReadyToDrawFirstPointOfEllipse;
import state.drawing_filled_ellipse.ReadyToDrawFirstPointOfFilledEllipse;
import state.drawing_filled_rectangle.ReadyToDrawFirstPointOfFilledRectangle;
import state.drawing_rectangle_state.ReadyToDrawFirstPointOfRectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

//TODO: singleton ?
abstract public class State {
    abstract public State mouseLeftClick(MouseEvent e);
    abstract public State mouseRightClick(MouseEvent e);

    public State mouseMove(MouseEvent e) {
        return this;
    }

    public State chooseModeButtonPressed() {
        return Idle.getInstance();
    }

    public State lineButtonPressed() {
        //TODO
        return null;
    }

    public State rectangleButtonPressed() {
        return ReadyToDrawFirstPointOfRectangle.getInstance();
    }

    public State ellipseButtonPressed() {
        return ReadyToDrawFirstPointOfEllipse.getInstance();
    }

    public State filledRectangleButtonPressed() {
        return ReadyToDrawFirstPointOfFilledRectangle.getInstance();
    }

    public State filledEllipseButtonPressed() {
//        System.out.println("filled ellipse button pressed");
        return ReadyToDrawFirstPointOfFilledEllipse.getInstance();
    }

    public State multipleLineButtonPressed() {
        //TODO
        return null;
    }

    public State textButtonPressed() {
        //TODO
        return null;
    }

    public State mouseDragged(MouseEvent e, Point direction) {
        return this;
    }

    public State mouseWheelMoved(int notches) {
        final double unitRate = 0.05;
        Shape shape = Model.getCurrentShape();
        if (shape != null) {
//            System.out.println("change size");
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
            //TODO: need to change?
            System.err.println(e.getMessage());
        }
    }

    protected void addShape(Shape shape) {
        Model.addShape(shape);
        Model.shapeListChanged();
    }
}
