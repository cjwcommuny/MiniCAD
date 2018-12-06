package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    final private static int GRID_UNIT_SIZE = 100;
    final private static int BUTTON_NUM = 8;
    final private static int DRAWING_PANEL_GRID_WIDTH = BUTTON_NUM;
    final private static int DRAWING_PANEL_GRID_HEIGHT = DRAWING_PANEL_GRID_WIDTH;
    final private static int BUTTON_GRID_WIDTH = 1;
    final private static int WIDTH = GRID_UNIT_SIZE * (DRAWING_PANEL_GRID_WIDTH + BUTTON_GRID_WIDTH);
    final private static int HEIGHT = GRID_UNIT_SIZE * DRAWING_PANEL_GRID_HEIGHT;

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

    public MainFrame() throws HeadlessException {
        setTitle("MiniCAD");
        addComponentsToPane(getContentPane());
        addListener();
    }

    private void addListener() {
        drawingPanel.addMouseListener(drawingPanel.mouseAdapter);
        drawingPanel.addMouseMotionListener(drawingPanel.mouseAdapter);
        rectangleButton.addActionListener(new ButtonListener());
    }

    private void addComponentsToPane(Container pane) {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
        addDrawingPanel(pane);
        addLineSegment(pane);
        addRectangleButton(pane);
        addEllipseButton(pane);
        addFilledRectangleButton(pane);
        addFilledEllipseButton(pane);
        addMultipleLineSegmentButton(pane);
        addSaveButton(pane);
    }

    private void addDrawingPanel(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridheight = DRAWING_PANEL_GRID_HEIGHT;
        pane.add(drawingPanel, constraints);
    }

    private void addLineSegment(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 0;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(lineSegmentButton, constraints);
    }

    private void addRectangleButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 1;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(rectangleButton, constraints);
    }

    private void addEllipseButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 2;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(ellipseButton, constraints);
    }

    private void addFilledRectangleButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 3;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(filledRectangleButton, constraints);
    }

    private void addFilledEllipseButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 4;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(filledEllipseButton, constraints);
    }

    private void addMultipleLineSegmentButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 5;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(multipleLineSegmentButton, constraints);
    }

    private void addTextBlockButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 6;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(textBlockButton, constraints);
    }

    private void addSaveButton(Container pane) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 1;
        constraints.gridx = DRAWING_PANEL_GRID_WIDTH;
        constraints.gridy = 7;
        constraints.gridwidth = BUTTON_GRID_WIDTH;
        pane.add(saveButton, constraints);
    }



    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
//        frame.setResizable(false);
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
}
