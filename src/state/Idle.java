package state;

import java.awt.event.MouseEvent;

public class Idle extends State {
    private static Idle thisState = new Idle();

    public static State getInstance() {
        return thisState;
    }

    @Override
    public State mouseLeftClick(MouseEvent e) {
        return new Idle();
    }

    @Override
    public State mouseMove(MouseEvent e) {
        return new Idle();
    }

    @Override
    public State mouseRightClick(MouseEvent e) {
        return new Idle();
    }

}
