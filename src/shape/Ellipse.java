package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {
    private Point firstPoint;
    private Point leftTopCorner;
    private Point rightBottomCorner;

//    public void setLeftTopCorner(Point leftTopCorner) {
//        this.leftTopCorner = leftTopCorner;
//    }

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

//    public Point getLeftTopCorner() {
//        return leftTopCorner;
//    }
//
//    public Point getRightBottomCorner() {
//        return rightBottomCorner;
//    }

    public Ellipse(Point firstPoint, Point secondPoint) {
        this.leftTopCorner = this.firstPoint = firstPoint;
        this.rightBottomCorner = secondPoint;
    }

    @Override
    public void render(Graphics2D imageGraphics) {
        int width = rightBottomCorner.x - leftTopCorner.x;
        int height = rightBottomCorner.y - leftTopCorner.y;
        Ellipse2D ellipse = new Ellipse2D.Double(leftTopCorner.x, leftTopCorner.y, width, height);
        imageGraphics.draw(ellipse);
    }
}
