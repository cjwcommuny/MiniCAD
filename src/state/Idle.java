package state;

import model.Model;

import java.awt.*;
import shape.Shape;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

public class Idle extends State {
    private static Idle thisState = new Idle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseDragged(MouseEvent e, Point direction) {
        findFocusShape(e);
        Shape shape = Model.getCurrentShape();
        if (shape != null) {
            shape.move(direction);
        }
        Model.shapeListChanged();
        return getInstance();
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        findFocusShape(e);
        Model.shapeListChanged();
        return new Idle();
    }

    private void findFocusShape(MouseEvent e) {
        Point point = e.getPoint();
        ListIterator<Shape> listIterator = Model.getShapeListIterator();
        boolean findFocus = false;
        while (listIterator.hasNext()) {
            Shape shape = listIterator.next();
            if (shape.isInShape(point)) {
                Model.setCurrentShape(shape);
                Model.shapeListChanged();
                findFocus = true;
                break;
            }
        }
        if (!findFocus) {
            Model.setCurrentShape(null);
        }
    }

    @Override
    public State mouseMove(MouseEvent e) {
        return new Idle();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return new Idle();
    }

}
