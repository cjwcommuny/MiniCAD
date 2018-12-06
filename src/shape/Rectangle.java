package shape;

import java.awt.*;

public class Rectangle extends Shape {
    private Point leftTopCorner;
    private Point rightBottomCorner;

    public void setLeftTopCorner(Point leftTopCorner) {
        this.leftTopCorner = leftTopCorner;
    }

    public void setRightBottomCorner(Point rightBottomCorner) {
        this.rightBottomCorner = rightBottomCorner;
    }

    public Point getLeftTopCorner() {
        return leftTopCorner;
    }

    public Point getRightBottomCorner() {
        return rightBottomCorner;
    }

    public Rectangle(Point leftTopCorner, Point rightBottomCorner) {
        this.leftTopCorner = leftTopCorner;
        this.rightBottomCorner = rightBottomCorner;
    }

    @Override
    public void render(Graphics2D imageGraphics) {
        imageGraphics.drawLine(leftTopCorner.x, leftTopCorner.y, rightBottomCorner.x, leftTopCorner.y);
        imageGraphics.drawLine(rightBottomCorner.x, leftTopCorner.y, rightBottomCorner.x, rightBottomCorner.y);
        imageGraphics.drawLine(rightBottomCorner.x, rightBottomCorner.y, leftTopCorner.x, rightBottomCorner.y);
        imageGraphics.drawLine(leftTopCorner.x, rightBottomCorner.y, leftTopCorner.x, leftTopCorner.y);
    }
}
