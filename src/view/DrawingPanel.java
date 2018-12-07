package view;

import model.Model;
import shape.Shape;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ListIterator;

public class DrawingPanel extends JPanel {
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
            State newState = Model.getCurrentState().mouseMove(e);
            Model.setCurrentState(newState);
        }
    };

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            Shape currentShape = Model.getCurrentShape();
            System.out.println("key released");
            if (currentShape == null) {
                return;
            }
            super.keyTyped(e);
            char keyChar = e.getKeyChar();
            switch (keyChar) {
                case '1':
                    //default color
                    currentShape.setColor(Shape.NORMAL_COLOR);
                    break;
                case '2':
                    //red
                    currentShape.setColor(Color.RED);
                    break;
                case '3':
                    //blue
                    currentShape.setColor(Color.BLUE);
                    break;
                case '4':
                    //yellow
                    currentShape.setColor(Color.YELLOW);
                    break;
                default:
                    //default color
                    currentShape.setColor(Shape.NORMAL_COLOR);
                    break;
            }
            Model.shapeListChanged();
        }
    };
}
