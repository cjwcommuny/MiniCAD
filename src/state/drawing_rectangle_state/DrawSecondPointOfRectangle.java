package state.drawing_rectangle_state;

import model.Model;
import shape.Rectangle;
import state.Idle;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawSecondPointOfRectangle extends State {
    private static DrawSecondPointOfRectangle thisState = new DrawSecondPointOfRectangle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        setRightCornerPointOfRectangle(currentPoint);
        return DrawFirstPointOfRectangle.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return DrawFirstPointOfRectangle.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: assert?
        setRightCornerPointOfRectangle(currentPoint);
        return DrawSecondPointOfRectangle.getInstance();
    }

    private void setRightCornerPointOfRectangle(Point point) {
        Rectangle rectangle = (Rectangle) Model.getCurrentShape();
        rectangle.setSecondPoint(point);
        Model.shapeListChanged();
    }

    @Override
    public State rectangleButtonPressed() {
        //TODO: cancel draw current shape, delete currentShape?
        return Idle.getInstance();
    }
}
