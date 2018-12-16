package state.drawing_rectangle_state;

import shape.Rectangle;
import state.Idle;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawFirstPointOfRectangle extends State {
    private static DrawFirstPointOfRectangle thisState = new DrawFirstPointOfRectangle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: should use different point?
        Rectangle rectangle = new Rectangle(currentPoint, new Point(currentPoint));
        addShape(rectangle);
        return DrawSecondPointOfRectangle.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return Idle.getInstance();
    }

}
