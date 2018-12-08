package state.drawing_polygon_state;

import shape.LineSegment;
import shape.Polygon;
import state.State;
import state.drawing_multipleline_state.ReadyToDrawInitialPointOfMutipleLine;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawInitialPointOfPolygon extends State {
    private static State thisState = new ReadyToDrawInitialPointOfPolygon();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = new LineSegment(point, new Point(point));
        Polygon polygon = new Polygon();
        polygon.addLine(line);
        addShape(polygon);
        return ReadyToDrawFirstPointOfPolygon.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }
}
