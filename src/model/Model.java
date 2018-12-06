package model;

import shape.Shape;
import state.Idle;
import state.State;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static State currentState = new Idle();
    private static Shape currentShape;
    private static List<Shape> shapeList= new LinkedList<>();

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        Model.currentState = currentState;
    }

    public static Shape getCurrentShape() {
        return currentShape;
    }

    public static void addShape(Shape shape) {
        shapeList.add(shape);
        currentShape = shape;
    }

    public static void removeShape() {
        shapeList.remove(currentShape);
        currentShape = null;
    }
}
