package view;

import model.Model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class MainFrame extends JFrame {
    final private static int GRID_UNIT_WIDTH_SIZE = 100;
    final private static int GRID_UNIT_HEIGHT_SIZE = 80;
    final private static int BUTTON_NUM = 10;
    final private static int DRAWING_PANEL_GRID_WIDTH = BUTTON_NUM;
    final private static int DRAWING_PANEL_GRID_HEIGHT = DRAWING_PANEL_GRID_WIDTH;
    final private static int BUTTON_GRID_WIDTH = 1;
    final private static int WIDTH = GRID_UNIT_WIDTH_SIZE * (DRAWING_PANEL_GRID_WIDTH + BUTTON_GRID_WIDTH);
    final private static int HEIGHT = GRID_UNIT_HEIGHT_SIZE * DRAWING_PANEL_GRID_HEIGHT;
    final private static double DRAWING_PANEL_WEIGHT_X = 0.8;
    final private static double DRAWING_PANEL_WEIGHT_Y = 1;
    final private static double BUTTON_WEIGHT_X = 0.1;
    final private static double BUTTON_WEIGHT_Y = 1;

    private DrawingPanel drawingPanel = new DrawingPanel();
    private ButtonGroup drawingButtonGroup = new ButtonGroup();
    private ButtonForDrawing chooseModeButton =
            new ButtonForDrawing("Choose Mode", "chooseModeButtonPressed");
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
    private SaveButton saveButton = new SaveButton("Save"); //wonh't change state
    private LoadButton loadButton = new LoadButton("Load"); //wonh't change state

    MainFrame() throws HeadlessException {
        setTitle("MiniCAD");
        addButtonsToGroup();
        addComponentsToPane(getContentPane());
        addListener();
    }

    private void addButtonsToGroup() {
        drawingButtonGroup.add(chooseModeButton);
        drawingButtonGroup.add(lineSegmentButton);
        drawingButtonGroup.add(rectangleButton);
        drawingButtonGroup.add(ellipseButton);
        drawingButtonGroup.add(filledRectangleButton);
        drawingButtonGroup.add(filledEllipseButton);
        drawingButtonGroup.add(multipleLineSegmentButton);
        drawingButtonGroup.add(textBlockButton);
    }

    private void addListener() {
        drawingPanel.addMouseListener(drawingPanel.mouseAdapter);
        drawingPanel.addMouseMotionListener(drawingPanel.mouseAdapter);
        drawingPanel.addKeyListener(drawingPanel.keyAdapter);
        chooseModeButton.addActionListener(new ButtonForDrawing.DrawingButtonListener());
        rectangleButton.addActionListener(new ButtonForDrawing.DrawingButtonListener());
        ellipseButton.addActionListener(new ButtonForDrawing.DrawingButtonListener());
        saveButton.addActionListener(new SaveFileButtonListener());
        loadButton.addActionListener(new LoadFileButtonListener());
    }

    private void addComponentsToPane(Container pane) {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        addDrawingPanel(pane);
        addButton(pane, 0, chooseModeButton);
        addButton(pane, 1, lineSegmentButton);
        addButton(pane, 2, rectangleButton);
        addButton(pane, 3, ellipseButton);
        addButton(pane, 4, filledRectangleButton);
        addButton(pane, 5, filledEllipseButton);
        addButton(pane, 6, multipleLineSegmentButton);
        addButton(pane, 7, textBlockButton);
        addButton(pane, 8, saveButton);
        addButton(pane, 9, loadButton);
    }

    private void addButton(Container pane, int index, AbstractButton button) {
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

//    public void popupDialog(String message) {
//
//    }

    class ShapeListChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            drawingPanel.render();
        }
    }

    class SaveFileButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter("Shape list serialization file (*.ser)",
                            Model.SERIALIZE_EXTENSION);
            chooser.addChoosableFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);
            int returnValueOfDialog = chooser.showSaveDialog(MainFrame.this);
            if (returnValueOfDialog == JFileChooser.APPROVE_OPTION) {
                String fileName = chooser.getSelectedFile().getName();
                if (!fileName.endsWith(Model.SERIALIZE_EXTENSION)) {
                    fileName = fileName + '.' + Model.SERIALIZE_EXTENSION;
                }
                String directory = chooser.getCurrentDirectory().toString();
                Model.saveShapes(directory, fileName);
            }
        }
    }

    class LoadFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter("Shape list serialization file (*.ser)",
                            Model.SERIALIZE_EXTENSION);
            chooser.addChoosableFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);
            int returnValueOfDialog = chooser.showOpenDialog(MainFrame.this);
            if (returnValueOfDialog == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                Model.loadShapes(file);
            }
        }
    }


}
