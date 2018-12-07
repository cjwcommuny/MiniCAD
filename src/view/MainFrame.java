package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainFrame extends JFrame {
    final private static int GRID_UNIT_SIZE = 100;
    final private static int BUTTON_NUM = 8;
    final private static int DRAWING_PANEL_GRID_WIDTH = BUTTON_NUM;
    final private static int DRAWING_PANEL_GRID_HEIGHT = DRAWING_PANEL_GRID_WIDTH;
    final private static int BUTTON_GRID_WIDTH = 1;
    final private static int WIDTH = GRID_UNIT_SIZE * (DRAWING_PANEL_GRID_WIDTH + BUTTON_GRID_WIDTH);
    final private static int HEIGHT = GRID_UNIT_SIZE * DRAWING_PANEL_GRID_HEIGHT;
    final private static double DRAWING_PANEL_WEIGHT_X = 0.8;
    final private static double DRAWING_PANEL_WEIGHT_Y = 1;
    final private static double BUTTON_WEIGHT_X = 0.2;
    final private static double BUTTON_WEIGHT_Y = 1;

    private DrawingPanel drawingPanel = new DrawingPanel();
    private ButtonForDrawing lineSegmentButton =
            new ButtonForDrawing("Line Segment", "lineButtonPressed");
    private ButtonForDrawing rectangleButton =
            new ButtonForDrawing("Rectangle", "rectangleButtonPressed");
    private ButtonForDrawing ellipseButton =
            new ButtonForDrawing("Ellipse", "ellipseButtonPressed");
    private ButtonForDrawing filledRectangleButton =
            new ButtonForDrawing("Filled Rectangle", "filledRectangleButtonPressed");
    private ButtonForDrawing filledEllipseButton =
            new ButtonForDrawing("Filled Rectangle", "filledEllipseButtonPressed");
    private ButtonForDrawing multipleLineSegmentButton =
            new ButtonForDrawing("Multiple Line Segment", "multipleLineButtonPressed");
    private ButtonForDrawing textBlockButton =
            new ButtonForDrawing("Text Block", "textButtonPressed");
    private ButtonForDrawing saveButton = new ButtonForDrawing("Save", "saveButtonPressed");

    MainFrame() throws HeadlessException {
        setTitle("MiniCAD");
        addComponentsToPane(getContentPane());
        addListener();
    }

    private void addListener() {
        drawingPanel.addMouseListener(drawingPanel.mouseAdapter);
        drawingPanel.addMouseMotionListener(drawingPanel.mouseAdapter);
        rectangleButton.addActionListener(new ButtonListener());
        ellipseButton.addActionListener(new ButtonListener());
    }

    private void addComponentsToPane(Container pane) {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        addDrawingPanel(pane);
        addButton(pane, 0, lineSegmentButton);
        addButton(pane, 1, rectangleButton);
        addButton(pane, 2, ellipseButton);
        addButton(pane, 3, filledRectangleButton);
        addButton(pane, 4, filledEllipseButton);
        addButton(pane, 5, multipleLineSegmentButton);
        addButton(pane, 6, textBlockButton);
        addButton(pane, 7, saveButton);
    }

    private void addButton(Container pane, int index, ButtonForDrawing button) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = BUTTON_WEIGHT_X;
        constraints.weighty = BUTTON_WEIGHT_Y;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = index;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(button, constraints);
    }

    private void addDrawingPanel(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = DRAWING_PANEL_WEIGHT_X;
        constraints.weighty = DRAWING_PANEL_WEIGHT_Y;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridheight = DRAWING_PANEL_GRID_HEIGHT;
        pane.add(drawingPanel, constraints);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Model.addPropertyChangeListener(frame.new ShapeListChangeListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ButtonForDrawing button = (ButtonForDrawing) e.getSource();
            String methodName = button.getMethodName();
            Model.getCurrentState().invoke(methodName);
        }
    }

    class ShapeListChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            drawingPanel.render();
        }
    }
}
