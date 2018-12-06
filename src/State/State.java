package State;


//TODO: singleton ?
abstract public class State {
    abstract State mouseLeftClick();
    abstract State mouseRightClick();

    public State lineButtonPressed() {

    }

    public State rectangleButtonPressed() {

    }

    public State ellipseButtonPressed() {

    }

    public State filledRectangleButtonPressed() {

    }

    public State filledEllipseButtonPressed() {

    }

    public State multipleLineButtonPressed() {

    }

    public State textButtonPressed() {

    }
}
