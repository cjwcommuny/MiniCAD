package state.drawing_filled_rectangle;

import model.Model;
import shape.FilledRectangle;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawSecondPointOfFilledRectangle extends State {
    private static State thisState = new ReadyToDrawSecondPointOfFilledRectangle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        setRightCornerPointOfRectangle(currentPoint);
        return ReadyToDrawFirstPointOfFilledRectangle.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return ReadyToDrawFirstPointOfFilledRectangle.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point currentPoint = e.getPoint();
        setRightCornerPointOfRectangle(currentPoint);
        return getInstance();
    }

    private void setRightCornerPointOfRectangle(Point point) {
        FilledRectangle rectangle = (FilledRectangle) Model.getCurrentShape();
        rectangle.setSecondPoint(point);
        Model.shapeListChanged();
    }
}
