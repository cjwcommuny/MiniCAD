package shape;

import java.awt.*;
import java.awt.geom.Point2D;

abstract class RectangleFrame extends Shape {
    protected Point2D firstPoint;
    protected Point2D leftTopCorner;
    protected Point2D rightBottomCorner;

    protected RectangleFrame(Point firstPoint, Point secondPoint) {
        this.leftTopCorner = this.firstPoint = firstPoint;
        this.rightBottomCorner = secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.leftTopCorner = new Point(
                (int) Math.min(firstPoint.getX(), secondPoint.x),
                (int) Math.min(firstPoint.getY(), secondPoint.y)
        );
        this.rightBottomCorner = new Point(
                (int) Math.max(firstPoint.getX(), secondPoint.x),
                (int) Math.max(firstPoint.getY(), secondPoint.y)
        );
    }

    @Override
    abstract public boolean isInShape(Point point);

    @Override
    public void changeSize(double rate) {
        Point center = new Point(
                (int) (rightBottomCorner.getX() + leftTopCorner.getX()) /2,
                (int) (rightBottomCorner.getY() + leftTopCorner.getY()) / 2
        );
        firstPoint.setLocation(((firstPoint.getX() - center.x) * (1 + rate)) + center.x,
                ((firstPoint.getY() - center.y) * (1 + rate)) + center.y);
        leftTopCorner.setLocation(((leftTopCorner.getX() - center.x) * (1 + rate)) + center.x,
                ((leftTopCorner.getY() - center.y) * (1 + rate)) + center.y);
        rightBottomCorner.setLocation(((rightBottomCorner.getX() - center.x) * (1 + rate)) + center.x,
                ((rightBottomCorner.getY() - center.y) * (1 + rate)) + center.y);
    }

    @Override
    public void move(Point direction) {
        firstPoint.setLocation(firstPoint.getX() + direction.x,
                firstPoint.getY() + direction.y);
        leftTopCorner.setLocation(leftTopCorner.getX() + direction.x,
                leftTopCorner.getY() + direction.y);
        rightBottomCorner.setLocation(rightBottomCorner.getX() + direction.x,
                rightBottomCorner.getY() + direction.y);
    }
}
