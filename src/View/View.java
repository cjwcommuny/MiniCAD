package View;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private DrawingPanel drawingPanel = new DrawingPanel();
    private JButton lineSegmentButton = new JButton("Line Segment");
    private JButton rectangleButton = new JButton("Rectangle");
    private JButton ellipseButton = new JButton("Ellipse");
    private JButton filledRectangleButton = new JButton("Filled Rectangle");
    private JButton filledEllipseButton = new JButton("Filled Rectangle");
    private JButton multipleLineSegmentButton = new JButton("Multiple Line Segment");
    private JButton textBlockButton = new JButton("Text Block");
    private JButton saveButton = new JButton("Save");

    public View() throws HeadlessException {
        setTitle("MiniCAD");
        setLayout(new FlowLayout());
        addComponent();

    }

    private void addComponent() {
        add(drawingPanel);
        add(lineSegmentButton);
        add(rectangleButton);
        add(ellipseButton);
        add(filledRectangleButton);
        add(filledEllipseButton);
        add(multipleLineSegmentButton);
        add(textBlockButton);
        add(saveButton);
    }

    public static void main(String[] args) {

    }
}
