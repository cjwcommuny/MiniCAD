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
    public State mouseDragged(Point direction) {
        Shape shape = Model.getCurrentShape();
        if (shape != null) {
            shape.move(direction);
        }
        Model.shapeListChanged();
        return getInstance();
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        ListIterator<Shape> listIterator = Model.getShapeListIterator();
        while (listIterator.hasNext()) {
            Shape shape = listIterator.next();
            if (shape.isInShape(point)) {
                Model.setCurrentShape(shape);
                Model.shapeListChanged();
                return new Idle();
            }
        }
        Model.setCurrentShape(null);
        Model.shapeListChanged();
        return new Idle();
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
