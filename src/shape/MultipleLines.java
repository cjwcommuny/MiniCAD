package shape;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class MultipleLines extends Shape {
    protected List<LineSegment> lineList = new LinkedList<>();

    public void addLine(LineSegment line) {
        lineList.add(line);
    }

    public LineSegment getLastLine() {
        return lineList.get(lineList.size() - 1);
    }

    public LineSegment getFirstLine() {
        return lineList.get(0);
    }

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        for (LineSegment line: lineList) {
            line.render(imageGraphics, isActivated);
        }
    }

    @Override
    public boolean isInShape(Point point) {
        for (LineSegment line: lineList) {
            if (line.isInShape(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changeSize(double rate) {
        if (lineList.size() >= 1) {
            Point originalSecondPoint = new Point(getFirstLine().getFirstPoint());
            for (LineSegment line: lineList) {
                line.changeSize(rate);
                line.move(new Point(
                    originalSecondPoint.x - line.getFirstPoint().x,
                        originalSecondPoint.y - line.getFirstPoint().y
                ));
                originalSecondPoint = line.getSecondPoint();
            }
        }
    }

    @Override
    public void move(Point direction) {
        for (LineSegment line: lineList) {
            line.move(direction);
        }
    }
}
