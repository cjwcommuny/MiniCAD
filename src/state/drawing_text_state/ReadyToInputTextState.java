package state.drawing_text_state;

import shape.Text;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyToInputTextState extends State {
    private static State thisState = new ReadyToInputTextState();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        Point point = e.getPoint();
        Text text = new Text(point);
        addShape(text);
        return FinishInputTextState.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return getInstance();
    }
}
