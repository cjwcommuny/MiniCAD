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
    public void render(Graphics2D imageGraphics) {
        int width = rightBottomCorner.x - leftTopCorner.x;
        int height = rightBottomCorner.y - leftTopCorner.y;
        imageGraphics.drawRect(leftTopCorner.x, leftTopCorner.y, width, height);
//        imageGraphics.drawLine(leftTopCorner.x, leftTopCorner.y, rightBottomCorner.x, leftTopCorner.y);
//        imageGraphics.drawLine(rightBottomCorner.x, leftTopCorner.y, rightBottomCorner.x, rightBottomCorner.y);
//        imageGraphics.drawLine(rightBottomCorner.x, rightBottomCorner.y, leftTopCorner.x, rightBottomCorner.y);
//        imageGraphics.drawLine(leftTopCorner.x, rightBottomCorner.y, leftTopCorner.x, leftTopCorner.y);
    }
}
