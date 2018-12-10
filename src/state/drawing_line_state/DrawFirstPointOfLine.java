package state.drawing_line_state;

import shape.LineSegment;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawFirstPointOfLine extends State {
    private static State thisState = new DrawFirstPointOfLine();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = new LineSegment(point, new Point(point));
        addShape(line);
        System.out.println("mouse first click for line");
        return DrawSecondPointOfLine.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }
}
