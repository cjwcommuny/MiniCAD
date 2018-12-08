package shape;

import java.awt.*;

abstract class RectangleFrame extends Shape {
    protected Point firstPoint;
    protected Point leftTopCorner;
    protected Point rightBottomCorner;

    protected RectangleFrame(Point firstPoint, Point secondPoint) {
        this.leftTopCorner = this.firstPoint = firstPoint;
        this.rightBottomCorner = secondPoint;
    }

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

    @Override
    abstract public boolean isInShape(Point point);

    @Override
    public void changeSize(double rate) {
        Point center = new Point((rightBottomCorner.x + leftTopCorner.x) /2,
                (rightBottomCorner.y + leftTopCorner.y) / 2);
        firstPoint.x = (int) ((firstPoint.x - center.x) * (1 + rate)) + center.x;
        firstPoint.y = (int) ((firstPoint.y - center.y) * (1 + rate)) + center.y;
        leftTopCorner.x = (int) ((leftTopCorner.x - center.x) * (1 + rate)) + center.x;
        leftTopCorner.y = (int) ((leftTopCorner.y - center.y) * (1 + rate)) + center.y;
        rightBottomCorner.x = (int) ((rightBottomCorner.x - center.x) * (1 + rate)) + center.x;
        rightBottomCorner.y = (int) ((rightBottomCorner.y - center.y) * (1 + rate)) + center.y;
    }

    @Override
    public void move(Point direction) {
        firstPoint.x += direction.x;
        firstPoint.y += direction.y;
        leftTopCorner.x += direction.x;
        leftTopCorner.y += direction.y;
        rightBottomCorner.x += direction.x;
        rightBottomCorner.y += direction.y;
    }
}
