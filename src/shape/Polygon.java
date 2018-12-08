package shape;

import java.awt.*;

public class Polygon extends MultipleLines {
    private java.awt.Polygon polygon = new java.awt.Polygon();

    @Override
    public void render(Graphics2D imageGraphics, boolean isActivated) {
        super.render(imageGraphics, isActivated);
        for (LineSegment line: lineList) {
            line.render(imageGraphics, isActivated);
        }
        if (lineList.size() >= 1) {
            LineSegment firstLine = getFirstLine();
            LineSegment lastLine = getLastLine();
            imageGraphics.drawLine(firstLine.getFirstPoint().x,
                    firstLine.getFirstPoint().y,
                    lastLine.getSecondPoint().x,
                    lastLine.getSecondPoint().y);
        }
//        System.out.println("draw polygon");
        imageGraphics.draw(polygon);
    }

    @Override
    public boolean isInShape(Point point) {
//        for (LineSegment line: lineList) {
//            polygon.addPoint(line.getFirstPoint().x, line.getFirstPoint().y);
//        }
//        if (lineList.size() >= 1) {
//            polygon.addPoint(getLastLine().getSecondPoint().x, getLastLine().getSecondPoint().y);
//        }
//        return polygon.contains(point);
        return super.isInShape(point);
    }
}
