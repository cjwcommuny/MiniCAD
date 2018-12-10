package state.drawing_line_state;

import model.Model;
import shape.LineSegment;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawSecondPointOfLine extends State {
    private static State thisState = new DrawSecondPointOfLine();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = (LineSegment) Model.getCurrentShape();
        line.setSecondPoint(point);
        return DrawFirstPointOfLine.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return DrawFirstPointOfLine.getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = (LineSegment) Model.getCurrentShape();
        line.setSecondPoint(point);
        Model.shapeListChanged();
        return getInstance();
    }
}
