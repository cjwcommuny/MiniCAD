package shape;

import java.awt.*;
import java.util.List;

public class Polygon extends Shape {
    private MultipleLines multipleLines = new MultipleLines();

    private List<LineSegment> getLineList() {
        return multipleLines.getLineList();
    }

    @Override
    public void changeSize(double rate) {
        multipleLines.changeSize(rate);
    }

    @Override
    public void move(Point direction) {
        multipleLines.move(direction);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        for (LineSegment line: getLineList()) {
            line.render(imageGraphics, isActivated);
        }
        drawLastLine(imageGraphics);
    }

    private void drawLastLine(Graphics2D imageGraphics) {
        if (getLineList().size() >= 1) {
            LineSegment firstLine = multipleLines.getFirstLine();
            LineSegment lastLine = multipleLines.getLastLine();
            imageGraphics.drawLine(firstLine.getFirstPoint().x,
                    firstLine.getFirstPoint().y,
                    lastLine.getSecondPoint().x,
                    lastLine.getSecondPoint().y);
        }
    }

    @Override
    public boolean isInShape(Point point) {
        return multipleLines.isInShape(point);
    }

    public void addEdge(LineSegment line) {
        multipleLines.addLine(line);
    }

    public LineSegment getLastEdge() {
        return multipleLines.getLastLine();
    }
}
