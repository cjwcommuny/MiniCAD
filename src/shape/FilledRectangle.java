package shape;

import java.awt.*;

public class FilledRectangle extends Rectangle {
    public FilledRectangle(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        fillShape(imageGraphics);
        super.render(imageGraphics, isActivated);
    }

    @Override
    protected void fillShape(Graphics2D imageGraphics) {
        super.fillShape(imageGraphics);
        int width = rightBottomCorner.x - leftTopCorner.x;
        int height = rightBottomCorner.y - leftTopCorner.y;
        imageGraphics.fillRect(leftTopCorner.x, leftTopCorner.y, width, height);
    }
}
