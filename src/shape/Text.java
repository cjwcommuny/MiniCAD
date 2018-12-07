package shape;

import java.awt.*;

public class Text extends Shape {
    @Override
    public boolean isInShape(Point point) {
        return false;
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {

    }
}
