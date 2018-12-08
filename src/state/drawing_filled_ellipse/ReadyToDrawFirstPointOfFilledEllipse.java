package state.drawing_filled_ellipse;

import shape.FilledEllipse;
import state.Idle;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawFirstPointOfFilledEllipse extends State {
    private static State thisState = new ReadyToDrawFirstPointOfFilledEllipse();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        FilledEllipse ellipse = new FilledEllipse(currentPoint, new Point(currentPoint));
        addShape(ellipse);
        return ReadyToDrawSecondPointOfFilledEllipse.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return Idle.getInstance();
    }
}
