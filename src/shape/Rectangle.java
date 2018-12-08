package shape;

import java.awt.*;

public class Rectangle extends RectangleFrame {

    public Rectangle(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        int width = rightBottomCorner.x - leftTopCorner.x;
        int height = rightBottomCorner.y - leftTopCorner.y;
        imageGraphics.drawRect(leftTopCorner.x, leftTopCorner.y, width, height);
    }

    @Override
    public boolean isInShape(Point point) {
        return point.x >= leftTopCorner.x &&
                point.x <= rightBottomCorner.x &&
                point.y >= leftTopCorner.y &&
                point.y <= rightBottomCorner.y;
    }
}
