package state.drawing_polygon_state;

import model.Model;
import shape.Polygon;
import shape.LineSegment;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.sun.webkit.event.WCKeyEvent.VK_RETURN;
import static java.awt.event.KeyEvent.VK_ENTER;

public class DrawSecondPointOfPolygon extends State {
    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }

    private static State thisState = new DrawSecondPointOfPolygon();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        Polygon polygon = (Polygon) Model.getCurrentShape();
        polygon.addEdge(new LineSegment(point, new Point(point)));
        return DrawFirstPointOfPolygon.getInstance();
    }

    @Override
    public State keyButtonReleased(int keyCode) {
        if (keyCode == VK_RETURN || keyCode == VK_ENTER) {
            return DrawInitialPointOfPolygon.getInstance();
        } else {
            return getInstance();
        }
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point point = e.getPoint();
        Polygon polygon = (Polygon) Model.getCurrentShape();
        LineSegment line = polygon.getLastEdge();
        line.setSecondPoint(point);
        Model.shapeListChanged();
        return getInstance();
    }
}
