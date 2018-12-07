package state.drawing_ellipse_state;

import model.Model;
import shape.Ellipse;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawSecondPointOfEllipse extends State {
    private static ReadyToDrawSecondPointOfEllipse thisState = new ReadyToDrawSecondPointOfEllipse();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        setRightCornerPointOfEllipse(currentPoint);
        return ReadToDrawFirstPointOfEllipse.getInstance();
    }

    private void setRightCornerPointOfEllipse(Point currentPoint) {
        Ellipse ellipse = (Ellipse) Model.getCurrentShape();
        ellipse.setSecondPoint(currentPoint);
        Model.shapeListChanged();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return ReadToDrawFirstPointOfEllipse.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: assert?
        setRightCornerPointOfEllipse(currentPoint);
        return getInstance();
    }
}
