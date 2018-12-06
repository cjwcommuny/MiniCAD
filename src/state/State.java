package state;


import java.awt.event.MouseEvent;

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
        //TODO
        return null;
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

    public void invoke(String methodName) {
        try {
            getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            //TODO: need to change?
            System.err.println(e.getMessage());
        }
    }
}
