package state.drawing_filled_rectangle;

import shape.FilledRectangle;
import state.Idle;
import state.State;
import sun.security.jca.GetInstance;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawFirstPointOfFilledRectangle extends State {
    private static State thisState = new ReadyToDrawFirstPointOfFilledRectangle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        FilledRectangle rectangle = new FilledRectangle(currentPoint, new Point(currentPoint));
        addShape(rectangle);
        return ReadyToDrawSecondPointOfFilledRectangle.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return Idle.getInstance();
    }
}
