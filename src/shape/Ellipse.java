package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends RectangleFrame {

    public Ellipse(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        int width = (int) (rightBottomCorner.getX() - leftTopCorner.getX());
        int height = (int) (rightBottomCorner.getY() - leftTopCorner.getY());
        Ellipse2D ellipse = new Ellipse2D.Double(leftTopCorner.getX(), leftTopCorner.getY(), width, height);
        imageGraphics.draw(ellipse);
    }

    @Override
    public boolean isInShape(Point point) {
        double horizontalAxis = (rightBottomCorner.getX() - leftTopCorner.getX()) / 2.0;
        double verticalAxis = (rightBottomCorner.getY() - leftTopCorner.getY()) / 2.0;
        double centerX = leftTopCorner.getX() + horizontalAxis;
        double centerY = leftTopCorner.getY() + verticalAxis;
        double x = point.x - centerX;
        double y = point.y - centerY;
        return ((x * x)/(horizontalAxis * horizontalAxis) + (y * y) / (verticalAxis * verticalAxis)) <= 1;
    }
}
