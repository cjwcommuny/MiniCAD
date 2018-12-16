package shape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class Text extends Shape {
    private double fontSize = 15;
    private Point2D position;
    private String fontName = "Menlo";
    private int fontStyle = Font.PLAIN;
    private Font font = new Font(fontName, fontStyle, (int) fontSize);
    private String text = "this is a text box";
    private static int textFramePadding = 5;
    private static int textFrameStrokeLineWidth = 1;
    private Rectangle2D textRectangle;
    private Rectangle2D rectangleFrame;

    public void setFontStyle(String fontName) {
        this.fontName = fontName;
        this.font = new Font(fontName, fontStyle, (int) fontSize);
    }

    public Text(Point position) {
        this.position = position;
        textRectangle = new Rectangle2D.Double(position.x, position.y, 0, 0);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void changeSize(double rate) {
        fontSize = fontSize * (1 + rate);
        font = new Font(fontName, fontStyle, (int) fontSize);
    }

    @Override
    public void move(Point direction) {
        position.setLocation(position.getX() + direction.x, position.getY() + direction.y);
    }

    @Override
    public boolean isInShape(Point point) {
        return point.x >= rectangleFrame.getX()
                && point.x <= rectangleFrame.getX() + rectangleFrame.getWidth()
                && point.y >= rectangleFrame.getY()
                && point.y <= rectangleFrame.getY() + rectangleFrame.getHeight();
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        imageGraphics.setColor(NORMAL_COLOR);
        FontMetrics fm = imageGraphics.getFontMetrics(font);
        textRectangle = fm.getStringBounds(text, imageGraphics);
        rectangleFrame = new Rectangle2D.Double(
                position.getX() - textFramePadding,
                position.getY() - textRectangle.getHeight() - textFramePadding,
                textRectangle.getWidth() + 2 * textFramePadding,
                textRectangle.getHeight() + 2 * textFramePadding
        );
        imageGraphics.setFont(font);
        imageGraphics.drawString(text, (int) position.getX(), (int) position.getY());
        if (isActivated) {
            imageGraphics.setStroke(new BasicStroke(textFrameStrokeLineWidth));
            imageGraphics.draw(rectangleFrame);
        }
    }
}
