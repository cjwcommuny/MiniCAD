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

    @Override
    public State keyButtonReleased(int keyCode) {
        String keyString = KeyEvent.getKeyText(keyCode);
        Text textShape = (Text) Model.getCurrentShape();
        String originalText = textShape.getText();
        System.out.println("###" + keyString + "###");
        if (keyCode == VK_BACKSPACE && originalText.length() >= 1) {
            textShape.setText(originalText.substring(0, originalText.length() - 1));
        } else if (keyCode == VK_SPACE) {
            textShape.setText(originalText + " ");
        } else if ((keyCode >= VK_0 && keyCode <= VK_9) || (keyCode >= VK_A && keyCode <= VK_Z)) {
            textShape.setText(originalText + keyString.toLowerCase());
        } else if (keyCode == VK_F7) {
            textShape.setFontStyle("Times");
            Model.shapeListChanged();
        } else if (keyCode == VK_F8) {
            textShape.setFontStyle("Menlo");
            Model.shapeListChanged();
        }
        Model.shapeListChanged();
        return getInstance();
    }
}
