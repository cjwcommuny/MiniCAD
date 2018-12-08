package shape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Text extends Shape {
    private int fontSize = 15;
    private Point position;
    private String fontName = "Menlo";
    private int fontStyle = Font.PLAIN;
    private Font font = new Font(fontName, fontStyle, fontSize);
    private String text = "this is a text box";//new String();
    private static int textFramePadding = 5;
    private static int textFrameStrokeLineWidth = 1;
    private Rectangle2D textRectangle;
    private Rectangle2D rectangleFrame;

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
        fontSize = (int) (fontSize * (1 + rate));
        font = new Font(fontName, fontStyle, fontSize);
    }

    @Override
    public void move(Point direction) {
        position.x += direction.x;
        position.y += direction.y;
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
        FontMetrics fm = imageGraphics.getFontMetrics(font);
        textRectangle = fm.getStringBounds(text, imageGraphics);
        rectangleFrame = new Rectangle2D.Double(
                position.x - textFramePadding,
                position.y - textRectangle.getHeight() - textFramePadding,
                textRectangle.getWidth() + 2 * textFramePadding,
                textRectangle.getHeight() + 2 * textFramePadding
        );
//        rectangleFrame = new Rectangle2D.Double(position.x,
//                position.y - textRectangle.getHeight(),
//                textRectangle.getWidth(),
//                textRectangle.getHeight());
        imageGraphics.setFont(font);
        imageGraphics.drawString(text, position.x, position.y);
        if (isActivated) {
            imageGraphics.setStroke(new BasicStroke(textFrameStrokeLineWidth));
            imageGraphics.draw(rectangleFrame);
        }
    }
}
