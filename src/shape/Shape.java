package shape;


import java.awt.*;
import java.io.Serializable;

abstract public class Shape implements Serializable {
    public static final Color NORMAL_COLOR = Color.BLACK;
    private static final int NORMAL_LINE_WIDTH = 5;
    private Color color = NORMAL_COLOR;
    private int lineWidth = NORMAL_LINE_WIDTH;

    public abstract boolean isInShape(Point point);

    public int getLineWidth() {
        return lineWidth;
    }

    abstract public void changeSize(double rate);

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    abstract public void move(Point direction);

    public void render(Graphics2D imageGraphics, boolean isActivated) {
        if (isActivated) {
            imageGraphics.setStroke(
                    new BasicStroke(lineWidth,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_BEVEL,
                            0,
                            new float[]{10},
                            0)
            );
            imageGraphics.setColor(Color.WHITE);
        } else {
            imageGraphics.setStroke(new BasicStroke(lineWidth));
            imageGraphics.setColor(color);
        }
    }
}
