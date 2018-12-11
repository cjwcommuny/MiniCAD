package model;

import shape.Shape;
import shape.Text;
import state.Idle;
import state.State;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Model {
    private final static int LINE_WIDTH_STEP = 1;
    private final static int SHAPE_MOVE_STEP = 5;
    public final static String SERIALIZE_EXTENSION = "ser";
    private static State currentState = new Idle();
    private static Shape currentShape;
    private static List<Shape> shapeList= new LinkedList<>();
    private static PropertyChangeSupport listeners = new PropertyChangeSupport(Model.class);

    public static void setCurrentShape(Shape currentShape) {
        Model.currentShape = currentShape;
    }

    public static void setShapeList(List<Shape> shapeList) {
        Model.shapeList = shapeList;
    }

    public static List<Shape> getShapeList() {
        return shapeList;
    }

    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
    }

    private static void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }

    public static void shapeListChanged() {
        firePropertyChange("shapeList", null, null);
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
        shapeListChanged();
    }

    public static void incrementLineWidthOfCurrentShape() {
        int originalLineWidth = currentShape.getLineWidth();
        currentShape.setLineWidth(originalLineWidth + LINE_WIDTH_STEP);
        shapeListChanged();
    }

    public static void decrementLineWidthOfCurrentShape() {
        int originalLineWidth = currentShape.getLineWidth();
        currentShape.setLineWidth(originalLineWidth - LINE_WIDTH_STEP);
        shapeListChanged();
    }

    public static void moveShapeUp() {
        currentShape.move(new Point(0, -SHAPE_MOVE_STEP));
        shapeListChanged();
    }

    public static void moveShapeDown() {
        currentShape.move(new Point(0, SHAPE_MOVE_STEP));
        shapeListChanged();
    }

    public static void moveShapeLeft() {
        currentShape.move(new Point(-SHAPE_MOVE_STEP, 0));
        shapeListChanged();
    }

    public static void moveShapeRight() {
        currentShape.move(new Point(SHAPE_MOVE_STEP, 0));
        shapeListChanged();
    }

    public static void saveShapes(String directory, String fileName) {
        File file = new File(directory, fileName);
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(shapeList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadShapes(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Shape> newList = (List<Shape>) in.readObject();
            //TODO: cannot cast
            in.close();
            fileIn.close();
            shapeList.addAll(newList);
            shapeListChanged();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            //TODO: open dialog to notify that cannot open file
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void setTextFontStyle(String style) {
        if (currentShape != null && currentShape.getClass() == Text.class) {
            Text textShape = (Text) currentShape;
            textShape.setFontStyle(style);
            Model.shapeListChanged();
        }
    }

    public static void setColor(Color color) {
        if (currentShape != null) {
            currentShape.setColor(color);
        }
        shapeListChanged();
    }
}
