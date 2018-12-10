package state.drawing_multipleline_state;

import shape.LineSegment;
import shape.MultipleLines;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawInitialPointOfMultipleLine extends State {
    private static State thisState = new DrawInitialPointOfMultipleLine();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = new LineSegment(point, new Point(point));
        MultipleLines multipleLines = new MultipleLines();
        multipleLines.addLine(line);
        addShape(multipleLines);
        return DrawFirstPointOfMultipleLine.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }
}
