package shape;

import java.awt.*;
import java.awt.geom.Point2D;

public class LineSegment extends Shape {
    private Point2D firstPoint;
    private Point2D unitVector = new Point2D.Double(0, 0);
    private double length;

    Point getFirstPoint() {
        return new Point((int) firstPoint.getX(), (int) firstPoint.getY());
    }

    public Point getSecondPoint() {
        int secondPointX;
        int secondPointY;
        if (unitVector.getX() == Double.POSITIVE_INFINITY
                || unitVector.getX() == Double.NEGATIVE_INFINITY) {
            secondPointX = (int) firstPoint.getX();
        } else {
            secondPointX = (int) (unitVector.getX() * length + firstPoint.getX());
        }
        if (unitVector.getY() == Double.POSITIVE_INFINITY || unitVector.getY() == Double.NEGATIVE_INFINITY) {
            secondPointY = (int) firstPoint.getY();
        } else {
            secondPointY = (int) (unitVector.getY() * length + firstPoint.getY());
        }
        return new Point(secondPointX, secondPointY);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
//        System.out.println(unitVector.getX() + " " + unitVector.getY());
//        System.out.println((unitVector.getY() * length) / (unitVector.getX() * length));
        super.render(imageGraphics, isActivated);
        Point secondPoint = getSecondPoint();
        Point firstPointInt = getFirstPoint();
        imageGraphics.drawLine(firstPointInt.x, firstPointInt.y, secondPoint.x, secondPoint.y);
    }

    public void setSecondPoint(Point secondPoint) {
        calculateLengthAndUnitVector(secondPoint);
    }

    private void calculateLengthAndUnitVector(Point secondPoint) {
        length = Math.sqrt(
                Math.pow(secondPoint.x - firstPoint.getX(), 2) +
                Math.pow(secondPoint.y - firstPoint.getY(), 2)
        );
        double xDiff = secondPoint.x - firstPoint.getX();
        double yDiff = secondPoint.y - firstPoint.getY();
        double unitVectorX;
        double unitVectorY;
        if (xDiff == 0 && length == 0) {
            unitVectorX = Double.POSITIVE_INFINITY;
        } else {
            unitVectorX = xDiff / length;
        }
        if (yDiff == 0 && length == 0) {
            unitVectorY = Double.POSITIVE_INFINITY;
        } else {
            unitVectorY = yDiff / length;
        }
        unitVector.setLocation(unitVectorX, unitVectorY);
    }

    public LineSegment(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        calculateLengthAndUnitVector(secondPoint);
    }

    @Override
    public boolean isInShape(Point point) {
        //TODO: complext shape frame
        Point secondPoint = getSecondPoint();
        Point firstPointInt = getFirstPoint();
        return point.x >= Math.min(firstPointInt.x, secondPoint.x)
                && point.x <= Math.max(firstPointInt.x, secondPoint.x)
                && point.y >= Math.min(firstPointInt.y, secondPoint.y)
                && point.y <= Math.max(firstPointInt.y, secondPoint.y);
    }

    @Override
    public void changeSize(double rate) {
//        Point center = new Point(
//                (firstPoint.x + secondPoint.x) / 2,
//                (firstPoint.y + secondPoint.y) / 2
//        );
//        double tan = ((double) firstPoint.y - secondPoint.y) / (firstPoint.x - secondPoint.x);
//        firstPoint.x = (int) ((firstPoint.x - center.x) * (1 + rate)) + center.x;
//        firstPoint.y = (int) ((firstPoint.x - center.x) * tan) + center.y;
//        secondPoint.x = (int) ((secondPoint.x - center.x) * (1 + rate)) + center.x;
//        secondPoint.y = (int) ((secondPoint.x - center.x) * tan) + center.y;
//        double tan = ((double) firstPoint.y - secondPoint.y) / (firstPoint.x - secondPoint.x);
//        double tempSecondPointX = firstPoint.x + (secondPoint.x - firstPoint.x) * (1 + rate);
//        secondPoint.y = firstPoint.y + (int) ((tempSecondPointX - firstPoint.x) * tan);
//        secondPoint.x = (int) tempSecondPointX;
//        System.out.println(tan);
        length = length * (1 + rate);

    }

    @Override
    public void move(Point direction) {
        firstPoint.setLocation(firstPoint.getX() + direction.x, firstPoint.getY() + direction.y);
//        firstPoint.x += direction.x;
//        firstPoint.y += direction.y;
//        secondPoint.x += direction.x;
//        secondPoint.y += direction.y;
    }
}
