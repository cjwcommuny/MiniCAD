package shape;

import java.awt.*;

public class Rectangle extends RectangleFrame {

    public Rectangle(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        int width = (int) (rightBottomCorner.getX() - leftTopCorner.getX());
        int height = (int) (rightBottomCorner.getY() - leftTopCorner.getY());
        imageGraphics.drawRect((int) leftTopCorner.getX(), (int) leftTopCorner.getY(), width, height);
    }

    @Override
    public boolean isInShape(Point point) {
        return point.x >= leftTopCorner.getX() &&
                point.x <= rightBottomCorner.getX() &&
                point.y >= leftTopCorner.getY() &&
                point.y <= rightBottomCorner.getY();
    }
}
