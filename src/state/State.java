package state;


import model.Model;
import shape.Shape;
import state.drawing_ellipse_state.ReadToDrawFirstPointOfEllipse;
import state.drawing_rectangle_state.ReadyToDrawFirstPointOfRectangle;

import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

//TODO: singleton ?
abstract public class State {
    abstract public State mouseLeftClick(MouseEvent e);
    abstract public State mouseRightClick(MouseEvent e);
    abstract public State mouseMove(MouseEvent e);

    public State lineButtonPressed() {
        //TODO
        return null;
    }

    public State rectangleButtonPressed() {
        return ReadyToDrawFirstPointOfRectangle.getInstance();
    }

    public State ellipseButtonPressed() {
        return ReadToDrawFirstPointOfEllipse.getInstance();
    }

    public State filledRectangleButtonPressed() {
        //TODO
        return null;
    }

    public State filledEllipseButtonPressed() {
        //TODO
        return null;
    }

    public State multipleLineButtonPressed() {
        //TODO
        return null;
    }

    public State textButtonPressed() {
        //TODO
        return null;
    }

    public State saveButtonPressed() {
        //TODO
        return null;
    }

    public State loadButtonPressed() {
        return null;
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
