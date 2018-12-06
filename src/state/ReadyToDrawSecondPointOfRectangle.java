package state;

import model.Model;
import shape.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawSecondPointOfRectangle extends State {
    private static ReadyToDrawSecondPointOfRectangle thisState = new ReadyToDrawSecondPointOfRectangle();

    static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        Rectangle rectangle = (Rectangle) Model.getCurrentShape();
        rectangle.setRightBottomCorner(currentPoint);
        return ReadyToDrawFirstPointOfRectangle.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return ReadyToDrawFirstPointOfRectangle.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: assert?
        Rectangle rectangle = (Rectangle) Model.getCurrentShape();
        rectangle.setRightBottomCorner(currentPoint);
        return ReadyToDrawSecondPointOfRectangle.getInstance();
    }

    @Override
    public State rectangleButtonPressed() {
        //TODO: cancel draw current shape, delete currentShape?
        return Idle.getInstance();
    }
}
