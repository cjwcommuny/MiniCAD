package state.drawing_polygon_state;

import model.Model;
import shape.Polygon;
import shape.LineSegment;
import state.State;
import state.drawing_multipleline_state.ReadyToDrawSecondPointOfMutipleLine;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.sun.webkit.event.WCKeyEvent.VK_RETURN;
import static java.awt.event.KeyEvent.VK_ENTER;

public class ReadyToDrawSecondPointOfPolygon extends State {
    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }

    private static State thisState = new ReadyToDrawSecondPointOfPolygon();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        Polygon polygon = (Polygon) Model.getCurrentShape();
        polygon.addLine(new LineSegment(point, new Point(point)));
        return ReadyToDrawFirstPointOfPolygon.getInstance();
    }

    @Override
    public State keyButtonReleased(int keyCode) {
        if (keyCode == VK_RETURN || keyCode == VK_ENTER) {
            return ReadyToDrawInitialPointOfPolygon.getInstance();
        } else {
            return getInstance();
        }
    }

    @Override
    public State mouseMove(MouseEvent e) {
        Point point = e.getPoint();
        Polygon polygon = (Polygon) Model.getCurrentShape();
        LineSegment line = polygon.getLastLine();
        line.setSecondPoint(point);
        Model.shapeListChanged();
        return getInstance();
    }
}
