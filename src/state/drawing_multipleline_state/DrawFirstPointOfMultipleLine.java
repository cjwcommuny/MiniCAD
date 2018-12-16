package state.drawing_multipleline_state;

import model.Model;
import shape.LineSegment;
import shape.MultipleLines;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.sun.webkit.event.WCKeyEvent.VK_RETURN;
import static java.awt.event.KeyEvent.VK_ENTER;

public class DrawFirstPointOfMultipleLine extends State {
    private static State thisState = new DrawFirstPointOfMultipleLine();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        MultipleLines multipleLines = (MultipleLines) Model.getCurrentShape();
        multipleLines.addLine(new LineSegment(point, new Point(point)));
        return DrawSecondPointOfMultipleLine.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        //TODO: redundant
        Point point = e.getPoint();
        MultipleLines multipleLines = (MultipleLines) Model.getCurrentShape();
        LineSegment line = multipleLines.getLastLine();
        line.setSecondPoint(point);
        Model.shapeListChanged();
        return getInstance();
    }

    @Override
    public State keyButtonReleased(int keyCode) {
        if (keyCode == VK_RETURN || keyCode == VK_ENTER) {
            return DrawInitialPointOfMultipleLine.getInstance();
        } else {
            return super.keyButtonReleased(keyCode);
        }
    }
}
