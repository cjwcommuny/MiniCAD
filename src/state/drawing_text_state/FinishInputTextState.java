package state.drawing_text_state;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Model;
import shape.Text;
import state.State;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.sun.glass.events.KeyEvent.VK_BACKSPACE;
import static com.sun.glass.events.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.*;

public class FinishInputTextState extends State {
    private static State thisState = new FinishInputTextState();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        return ReadyToInputTextState.getInstance();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return ReadyToInputTextState.getInstance();
    }
}
