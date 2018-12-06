package state;

import com.sun.org.apache.xpath.internal.operations.Mod;
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
        setRightCornerPointOfRectangle(currentPoint);
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
        setRightCornerPointOfRectangle(currentPoint);
        return ReadyToDrawSecondPointOfRectangle.getInstance();
    }

    private void setRightCornerPointOfRectangle(Point point) {
        Rectangle rectangle = (Rectangle) Model.getCurrentShape();
        rectangle.setRightBottomCorner(point);
        Model.shapeListChanged();
    }

    @Override
    public State rectangleButtonPressed() {
        //TODO: cancel draw current shape, delete currentShape?
        return Idle.getInstance();
    }
}
