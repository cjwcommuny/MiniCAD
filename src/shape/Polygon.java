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
            line.setColor(super.getColor());
            line.render(imageGraphics, isActivated);
        }
        drawLastLine(imageGraphics, isActivated);
    }

    private void drawLastLine(Graphics2D imageGraphics, boolean isActivated) {
        if (getLineList().size() >= 1) {
            LineSegment firstLine = multipleLines.getFirstLine();
            LineSegment lastLine = multipleLines.getLastLine();
            LineSegment lineSegment = new LineSegment(firstLine.getFirstPoint(), lastLine.getSecondPoint());
            lineSegment.setColor(super.getColor());
            lineSegment.render(imageGraphics, isActivated);
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

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        multipleLines.setColor(color);
    }
}
