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
    final private static int GRID_UNIT_HEIGHT_SIZE = 70;
    final private static int DRAWING_BUTTON_NUM = 9;
    final private static int FUNCTION_BUTTON_NUM = 2;
    final private static int BUTTON_NUM = DRAWING_BUTTON_NUM + FUNCTION_BUTTON_NUM;
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
    private SaveButton saveButton = new SaveButton("Save");
    private LoadButton loadButton = new LoadButton("Load");

    //should use a hashmap ?
    private ButtonForDrawing[] drawingButtons = new ButtonForDrawing[]{
            new ButtonForDrawing("Choose Mode", "chooseModeButtonPressed"),
            new ButtonForDrawing("Line Segment", "lineButtonPressed"),
            new ButtonForDrawing("Rectangle", "rectangleButtonPressed"),
            new ButtonForDrawing("Ellipse", "ellipseButtonPressed"),
            new ButtonForDrawing("Filled Rectangle", "filledRectangleButtonPressed"),
            new ButtonForDrawing("Filled Ellipse", "filledEllipseButtonPressed"),
            new ButtonForDrawing("Multiple Line Segment", "multipleLineButtonPressed"),
            new ButtonForDrawing("Polygon", "polygonButtonPressed"),
            new ButtonForDrawing("Text Block", "textButtonPressed"),
    };

    MainFrame() throws HeadlessException {
        setTitle("MiniCAD");
        addButtonsToGroup();
        addComponentsToPane(getContentPane());
        addListener();
        initializeButtonState();
    }

    private void initializeButtonState() {
        //select choose mode at the begin
        drawingButtons[0].setSelected(true);
    }

    private void addButtonsToGroup() {
        for (ButtonForDrawing button: drawingButtons) {
            drawingButtonGroup.add(button);
        }
    }

    private void addListener() {
        addListenerForDrawingPanel();
        addListenerForButtons();
    }

    private void addListenerForDrawingPanel() {
        drawingPanel.addMouseListener(drawingPanel.mouseAdapter);
        drawingPanel.addMouseMotionListener(drawingPanel.mouseAdapter);
        drawingPanel.addMouseWheelListener(drawingPanel.mouseAdapter);
        drawingPanel.addKeyListener(drawingPanel.keyAdapter);
    }

    private void addListenerForButtons() {
        for (ButtonForDrawing button: drawingButtons) {
            button.addActionListener(new ButtonForDrawing.DrawingButtonListener());
        }
        saveButton.addActionListener(new SaveFileButtonListener());
        loadButton.addActionListener(new LoadFileButtonListener());
    }

    private void addComponentsToPane(Container pane) {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        addDrawingPanel(pane);
        addAllButtons(pane);
    }

    private void addAllButtons(Container pane) {
        int i = 0;
        for (; i < DRAWING_BUTTON_NUM; ++i) {
            addButton(pane, i, drawingButtons[i]);
        }
        addButton(pane, i, saveButton);
        addButton(pane, i + 1, loadButton);
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

    class ShapeListChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            drawingPanel.render();
        }
    }

    class SaveFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = createSaveLoadFileChooser();
            int returnValueOfDialog = chooser.showSaveDialog(MainFrame.this);
            if (returnValueOfDialog == JFileChooser.APPROVE_OPTION) {
                String fileName = getSelectedFileName(chooser);
                String directory = chooser.getCurrentDirectory().toString();
                Model.saveShapes(directory, fileName);
            }
        }

        private String getSelectedFileName(JFileChooser chooser) {
            String fileName = chooser.getSelectedFile().getName();
            if (!fileName.endsWith(Model.SERIALIZE_EXTENSION)) {
                fileName = fileName + '.' + Model.SERIALIZE_EXTENSION;
            }
            return fileName;
        }
    }

    private JFileChooser createSaveLoadFileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("Shape list serialization file (*.ser)",
                        Model.SERIALIZE_EXTENSION);
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser;
    }

    class LoadFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = createSaveLoadFileChooser();
            int returnValueOfDialog = chooser.showOpenDialog(MainFrame.this);
            if (returnValueOfDialog == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                Model.loadShapes(file);
            }
        }
    }


}
