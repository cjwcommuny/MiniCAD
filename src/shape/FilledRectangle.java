package shape;

import java.awt.*;

public class FilledRectangle extends Rectangle implements Fillable {
    public FilledRectangle(Point firstPoint, Point secondPoint) {
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
        imageGraphics.fillRect((int) leftTopCorner.getX(), (int) leftTopCorner.getY(), width, height);
    }
}
