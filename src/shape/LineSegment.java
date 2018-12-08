package shape;

import java.awt.*;

public class LineSegment extends Shape {
    private Point firstPoint;
    private Point secondPoint;

    Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        imageGraphics.drawLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    public LineSegment(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public boolean isInShape(Point point) {
        //TODO: complext shape frame
        return point.x >= Math.min(firstPoint.x, secondPoint.x)
                && point.x <= Math.max(firstPoint.x, secondPoint.x)
                && point.y >= Math.min(firstPoint.y, secondPoint.y)
                && point.y <= Math.max(firstPoint.y, secondPoint.y);
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
        double tan = ((double) firstPoint.y - secondPoint.y) / (firstPoint.x - secondPoint.x);
        double tempSecondPointX = firstPoint.x + (secondPoint.x - firstPoint.x) * (1 + rate);
        secondPoint.y = firstPoint.y + (int) ((tempSecondPointX - firstPoint.x) * tan);
        secondPoint.x = (int) tempSecondPointX;
        System.out.println(tan);
    }

    @Override
    public void move(Point direction) {
        firstPoint.x += direction.x;
        firstPoint.y += direction.y;
        secondPoint.x += direction.x;
        secondPoint.y += direction.y;
    }
}
