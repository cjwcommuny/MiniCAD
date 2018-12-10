package state.drawing_polygon_state;

import shape.LineSegment;
import shape.Polygon;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawInitialPointOfPolygon extends State {
    private static State thisState = new DrawInitialPointOfPolygon();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = new LineSegment(point, new Point(point));
        Polygon polygon = new Polygon();
        polygon.addEdge(line);
        addShape(polygon);
        return DrawFirstPointOfPolygon.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }
}
