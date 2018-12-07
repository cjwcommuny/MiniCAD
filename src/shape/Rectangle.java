package shape;

import java.awt.*;

public class Rectangle extends Shape {
    private Point firstPoint;
    private Point leftTopCorner;
    private Point rightBottomCorner;

    public void setSecondPoint(Point secondPoint) {
        this.leftTopCorner = new Point(
                Math.min(firstPoint.x, secondPoint.x),
                Math.min(firstPoint.y, secondPoint.y)
        );
        this.rightBottomCorner = new Point(
                Math.max(firstPoint.x, secondPoint.x),
                Math.max(firstPoint.y, secondPoint.y)
        );
    }

    public Rectangle(Point firstPoint, Point secondPoint) {
        this.leftTopCorner = this.firstPoint = firstPoint;
        this.rightBottomCorner = secondPoint;
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
