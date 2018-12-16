package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FilledEllipse extends Ellipse implements Fillable {
    public FilledEllipse(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        fillShape(imageGraphics);
        super.render(imageGraphics, isActivated);
    }

    @Override
    public void fillShape(Graphics2D imageGraphics) {
        super.setDefaultGraphics(imageGraphics);
        int width = (int) (rightBottomCorner.getX() - leftTopCorner.getX());
        int height = (int) (rightBottomCorner.getY() - leftTopCorner.getY());
        Ellipse2D ellipse = new Ellipse2D.Double(leftTopCorner.getX(), leftTopCorner.getY(), width, height);
        imageGraphics.fill(ellipse);
    }
}
