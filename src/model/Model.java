package model;

import shape.Shape;
import state.Idle;
import state.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Model {
    private static State currentState = new Idle();
    private static Shape currentShape;
    private static List<Shape> shapeList= new LinkedList<>();
    private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }

    public static ListIterator<Shape> getShapeListIterator() {
        return shapeList.listIterator();
    }

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
