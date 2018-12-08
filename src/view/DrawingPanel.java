package view;

import model.Model;
import shape.Shape;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ListIterator;

import static java.awt.event.KeyEvent.*;

public class DrawingPanel extends JPanel {
    private Point start = new Point();
    private Point end = new Point();

    DrawingPanel() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        renderAllShapes(g2);
        renderActivatedShape(g2);
    }

    void render() {
        repaint();
    }

    private void renderAllShapes(Graphics2D imageGraphics) {
        ListIterator<Shape> shapeListIterator = Model.getShapeListIterator();
        while (shapeListIterator.hasNext()) {
            Shape currentShape = shapeListIterator.next();
            currentShape.render(imageGraphics, false);
        }
    }

    private void renderActivatedShape(Graphics2D imageGraphics) {
        if (Model.getCurrentShape() != null) {
            Model.getCurrentShape().render(imageGraphics, true);
        }
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            requestFocusInWindow();
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
            int notches = e.getWheelRotation();
//            System.out.println("mouse wheel moved " + notches);
            State newState = Model.getCurrentState().mouseWheelMoved(notches);
            Model.setCurrentState(newState);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            end = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            start = null;
            end = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            start = end;
            end = e.getPoint();
            State newState = Model.getCurrentState().mouseDragged(
                    e,
                    new Point(end.x - start.x, end.y - start.y)
            );
            Model.setCurrentState(newState);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            State newState = Model.getCurrentState().mouseMove(e);
            Model.setCurrentState(newState);
        }
    };

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            State newState = Model.getCurrentState().keyButtonReleased(e.getKeyCode());
            Model.setCurrentState(newState);
        }
    };
}
