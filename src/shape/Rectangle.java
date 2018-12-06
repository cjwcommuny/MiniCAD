package shape;

import java.awt.*;

public class Rectangle extends Shape {
    private Point leftUpperCorner;
    private Point rightBottomCorner;

    public void setLeftUpperCorner(Point leftUpperCorner) {
        this.leftUpperCorner = leftUpperCorner;
    }

    public void setRightBottomCorner(Point rightBottomCorner) {
        this.rightBottomCorner = rightBottomCorner;
    }

    public Point getLeftUpperCorner() {
        return leftUpperCorner;
    }

    public Point getRightBottomCorner() {
        return rightBottomCorner;
    }

    public Rectangle(Point leftUpperCorner, Point rightBottomCorner) {
        this.leftUpperCorner = leftUpperCorner;
        this.rightBottomCorner = rightBottomCorner;
    }

    @Override
    public void render() {

    }
}
