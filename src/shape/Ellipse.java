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
        int width = rightBottomCorner.x - leftTopCorner.x;
        int height = rightBottomCorner.y - leftTopCorner.y;
        Ellipse2D ellipse = new Ellipse2D.Double(leftTopCorner.x, leftTopCorner.y, width, height);
        imageGraphics.draw(ellipse);
    }

    @Override
    public boolean isInShape(Point point) {
        double horizontalAxis = (rightBottomCorner.x - leftTopCorner.x) / 2.0;
        double verticalAxis = (rightBottomCorner.y - leftTopCorner.y) / 2.0;
        double centerX = leftTopCorner.x + horizontalAxis;
        double centerY = leftTopCorner.y + verticalAxis;
        double x = point.x - centerX;
        double y = point.y - centerY;
        return ((x * x)/(horizontalAxis * horizontalAxis) + (y * y) / (verticalAxis * verticalAxis)) <= 1;
    }
}
