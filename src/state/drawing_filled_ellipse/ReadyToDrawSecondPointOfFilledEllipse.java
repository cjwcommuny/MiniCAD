package state.drawing_filled_ellipse;

import model.Model;
import shape.FilledEllipse;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawSecondPointOfFilledEllipse extends State {
    private static State thisState = new ReadyToDrawSecondPointOfFilledEllipse();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        setRightCornerPointOfEllipse(currentPoint);
        return ReadyToDrawFirstPointOfFilledEllipse.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return ReadyToDrawFirstPointOfFilledEllipse.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: assert?
        setRightCornerPointOfEllipse(currentPoint);
        return getInstance();
    }

    private void setRightCornerPointOfEllipse(Point currentPoint) {
        FilledEllipse ellipse = (FilledEllipse) Model.getCurrentShape();
        ellipse.setSecondPoint(currentPoint);
        Model.shapeListChanged();
    }
}
