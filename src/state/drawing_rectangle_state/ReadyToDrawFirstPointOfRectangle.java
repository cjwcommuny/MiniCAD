package state.drawing_rectangle_state;

import model.Model;
import shape.Rectangle;
import state.Idle;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawFirstPointOfRectangle extends State {
    private static ReadyToDrawFirstPointOfRectangle thisState = new ReadyToDrawFirstPointOfRectangle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: should use different point?
        Rectangle rectangle = new Rectangle(currentPoint, new Point(currentPoint));
        addShape(rectangle);
        return ReadyToDrawSecondPointOfRectangle.getInstance();
    }



    @Override
    public State mouseRightClick(MouseEvent e) {
        return Idle.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        return ReadyToDrawFirstPointOfRectangle.getInstance();
    }
}
