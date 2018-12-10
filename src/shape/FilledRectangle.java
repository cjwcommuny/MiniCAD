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
        int width = rightBottomCorner.x - leftTopCorner.x;
        int height = rightBottomCorner.y - leftTopCorner.y;
        imageGraphics.fillRect(leftTopCorner.x, leftTopCorner.y, width, height);
    }
}
