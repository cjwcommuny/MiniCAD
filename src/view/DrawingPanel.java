package view;

import model.Model;
import shape.Shape;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class DrawingPanel extends JPanel {
    private static final Color NORMAL_COLOR = Color.BLACK;
    private static final Color ACTIVATED_COLOR = Color.RED;
    private BufferedImage image = new BufferedImage(MainFrame.DRAWING_PANEL_WIDTH,
            MainFrame.DRAWING_PANEL_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
    private Graphics2D imageGraphics = image.createGraphics();

    DrawingPanel() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, null, 0, 0);
    }

    void render() {
        renderAllShapes();
        renderActivatedShape();
//        imageGraphics.setColor(Color.BLACK);
//        imageGraphics.setStroke(new BasicStroke());
//        imageGraphics.drawLine(0, 0, 100, 100);
        repaint();
    }

    private void renderAllShapes() {
        ListIterator<Shape> shapeListIterator = Model.getShapeListIterator();
        imageGraphics.setColor(NORMAL_COLOR);
        while (shapeListIterator.hasNext()) {
            Shape currentShape = shapeListIterator.next();
            currentShape.render(imageGraphics);
        }
    }

    private void renderActivatedShape() {
        imageGraphics.setColor(ACTIVATED_COLOR);
        Model.getCurrentShape().render(imageGraphics);
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            State newState;
            if (SwingUtilities.isLeftMouseButton(e)) {
                newState = Model.getCurrentState().mouseLeftClick(e);
            } else {
                newState = Model.getCurrentState().mouseRightClick(e);
            }
            //TODO: should this instruction be encapsulated?
            Model.setCurrentState(newState);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e);
            //TODO
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            //TODO
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            System.out.println(e.getPoint());
            State newState = Model.getCurrentState().mouseMove(e);
            Model.setCurrentState(newState);
        }
    };
}
