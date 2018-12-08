package shape;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class MutipleLine extends Shape {
    private List<LineSegment> lineList = new LinkedList<>();

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
        for (LineSegment line: lineList) {
            line.changeSize(rate);
        }
    }

    @Override
    public void move(Point direction) {
        for (LineSegment line: lineList) {
            line.move(direction);
        }
    }
}
