package State;

public class Idle extends State {
    @Override
    State mouseLeftClick() {
        return new Idle();
    }

    @Override
    State mouseRightClick() {
        return new Idle();
    }
}
