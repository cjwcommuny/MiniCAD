package state.drawing_ellipse_state;

import shape.Ellipse;
import state.Idle;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawFirstPointOfEllipse extends State {
    private static DrawFirstPointOfEllipse thisState = new DrawFirstPointOfEllipse();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point currentPoint = e.getPoint();
        //TODO: should use different point?
        Ellipse ellipse = new Ellipse(currentPoint, new Point(currentPoint));
        addShape(ellipse);
        return DrawSecondPointOfEllipse.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return Idle.getInstance();
    }

//    @Override
//    public State mouseMove(MouseEvent e) {
//        return getInstance();
//    }
}
