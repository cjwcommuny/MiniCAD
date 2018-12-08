package state.drawing_multipleline_state;

import model.Model;
import shape.LineSegment;
import shape.MutipleLine;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToDrawInitialPointOfMutipleLine extends State {
    private static State thisState = new ReadyToDrawInitialPointOfMutipleLine();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        LineSegment line = new LineSegment(point, new Point(point));
        MutipleLine mutipleLine = new MutipleLine();
        mutipleLine.addLine(line);
        addShape(mutipleLine);
        return ReadyToDrawFirstPointOfMutipleLine.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }
}
