package state;


import model.Model;
import shape.Shape;
import state.drawing_ellipse_state.ReadyToDrawFirstPointOfEllipse;
import state.drawing_filled_ellipse.ReadyToDrawFirstPointOfFilledEllipse;
import state.drawing_filled_rectangle.ReadyToDrawFirstPointOfFilledRectangle;
import state.drawing_rectangle_state.ReadyToDrawFirstPointOfRectangle;
import state.drawing_text_state.ReadyToInputTextState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import static java.awt.event.KeyEvent.*;

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
        return ReadyToInputTextState.getInstance();
    }

    public State keyButtonReleased(int keyCode) {
        Shape currentShape = Model.getCurrentShape();
//        System.out.println("key released");
        if (currentShape == null) {
            return this;
        }
        switch (keyCode) {
            case VK_F3:
                //default color
                currentShape.setColor(Shape.NORMAL_COLOR);
                break;
            case VK_F4:
                //red
                currentShape.setColor(Color.RED);
                break;
            case VK_F5:
                //blue
                currentShape.setColor(Color.BLUE);
                break;
            case VK_F6:
                //yellow
                currentShape.setColor(Color.YELLOW);
                break;
            case VK_BACK_SPACE: case VK_DELETE:
                Model.removeShape();
                break;
            case VK_F1:
                Model.incrementLineWidthOfCurrentShape();
                break;
            case VK_F2:
                Model.decrementLineWidthOfCurrentShape();
                break;
            default:
                break;
        }
        Model.shapeListChanged();
        return this;
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
