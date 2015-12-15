package gui;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import util.AutoClicker;
import util.data.ButtonCodeEnum;
import util.data.Operation;
import util.data.OperationTypeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AutoClickerFrame extends JFrame implements NativeKeyListener {

    public AutoClickerFrame(AutoClicker autoClicker) {

        this.autoClicker = autoClicker;

        initComponents();
        addFunctionGui();
        addMenuFunction();

        this.setVisible(true);
    }

    private AutoClicker autoClicker;
    private DefaultListModel<Operation> addedActionListModel = new DefaultListModel<>();

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuitem;
    private JMenuItem exitMenuItem;
    private JMenu helpMenu;
    private JMenuItem infoMenuItem;
    private JMenuItem aboutMenuItem;

    private JLabel autoClickerLabel;
    private JLabel addActionLabel;
    private JComboBox<OperationTypeEnum> actionComboBox;
    private JTextField delayField;
    private JLabel delayLabel;
    private JLabel xLabel;
    private JTextField xField;
    private JLabel yLabel;
    private JTextField yField;
    private JLabel startXLabel;
    private JTextField startXField;
    private JLabel startYLabel;
    private JTextField startYField;
    private JLabel endXLabel;
    private JTextField endXField;
    private JLabel endYLabel;
    private JTextField endYField;
    private JLabel keyCodeLabel;
    private JTextField keyCodeField;
    private JLabel buttonCodeLabel;
    private JCheckBox captureFullScreenBox;
    private JLabel wheelAmtLabel;
    private JTextField wheelAmtField;
    private JButton getMousePosButton;
    private JTextField xCoordMouse;
    private JTextField yCoordMouse;
    private JButton addActionButton;
    private JLabel actionsLabel;
    private JLabel autoDelayLabel;
    private JTextField autoDelayField;
    private JLabel amountRepeatsLabel;
    private JTextField repeatField;
    private JScrollPane actionsScrollPane;
    private JList<Operation> actionsList;
    private JButton deleteActionButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton updateActionButton;
    private JComboBox<ButtonCodeEnum> buttonComboBox;

    private void initComponents() {

        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        openMenuItem = new JMenuItem();
        saveMenuitem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        infoMenuItem = new JMenuItem();
        aboutMenuItem = new JMenuItem();

        autoClickerLabel = new JLabel();
        addActionLabel = new JLabel();
        actionComboBox = new JComboBox<>(OperationTypeEnum.values());
        delayField = new JTextField();
        delayLabel = new JLabel();
        xLabel = new JLabel();
        xField = new JTextField();
        yLabel = new JLabel();
        yField = new JTextField();
        startXLabel = new JLabel();
        startXField = new JTextField();
        startYLabel = new JLabel();
        startYField = new JTextField();
        endXLabel = new JLabel();
        endXField = new JTextField();
        endYLabel = new JLabel();
        endYField = new JTextField();
        keyCodeLabel = new JLabel();
        keyCodeField = new JTextField();
        buttonCodeLabel = new JLabel();
        captureFullScreenBox = new JCheckBox();
        wheelAmtLabel = new JLabel();
        wheelAmtField = new JTextField();
        getMousePosButton = new JButton();
        xCoordMouse = new JTextField();
        yCoordMouse = new JTextField();
        addActionButton = new JButton();
        actionsLabel = new JLabel();
        autoDelayLabel = new JLabel();
        autoDelayField = new JTextField();
        amountRepeatsLabel = new JLabel();
        repeatField = new JTextField();
        actionsScrollPane = new JScrollPane();
        actionsList = new JList<>(addedActionListModel);
        deleteActionButton = new JButton();
        updateActionButton = new JButton();
        startButton = new JButton();
        stopButton = new JButton();
        buttonComboBox = new JComboBox<>(ButtonCodeEnum.values());

        //======== this ========
        setTitle("AutoClicker");
        Container contentPane = getContentPane();

        //======== menuBar ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- openMenuItem ----
                openMenuItem.setText("Open");
                fileMenu.add(openMenuItem);

                //---- saveMenuitem ----
                saveMenuitem.setText("Save");
                fileMenu.add(saveMenuitem);

                //---- exitMenuItem ----
                exitMenuItem.setText("Exit");
                fileMenu.add(exitMenuItem);
            }
            menuBar.add(fileMenu);

            //======== helpMenu ========
            {
                helpMenu.setText("Help");

                //---- infoMenuItem ----
                infoMenuItem.setText("Info");
                helpMenu.add(infoMenuItem);

                //---- aboutMenuItem ----
                aboutMenuItem.setText("About");
                helpMenu.add(aboutMenuItem);
            }
            menuBar.add(helpMenu);
        }
        setJMenuBar(menuBar);

        //---- autoClickerLabel ----
        autoClickerLabel.setText("AutoClicker");
        autoClickerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        autoClickerLabel.setFont(new Font("Gabriola", Font.BOLD, 28));
        autoClickerLabel.setForeground(Color.red);

        //---- addActionLabel ----
        addActionLabel.setText("Add actions: ");
        addActionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

        //---- actionsComboBox ----
        actionComboBox.setMaximumRowCount(11);

        //---- delayField ----
        delayField.setToolTipText("Costum delay between 2 actions in ms.");

        //---- delayLabel ----
        delayLabel.setText("Delay: ");

        //---- xLabel ----
        xLabel.setText("X: ");

        //---- xField ----
        xField.setToolTipText("X co\u00f6rdinate to move mouse to.");

        //---- yLabel ----
        yLabel.setText("Y: ");

        //---- yField ----
        yField.setToolTipText("Y co\u00f6rdinate to move mouse to.");

        //---- startXLabel ----
        startXLabel.setText("Start X: ");

        //---- startXField ----
        startXField.setToolTipText("X co\u00f6rdinate from upper left corner of screenshot area.");

        //---- startYLabel ----
        startYLabel.setText("Start Y: ");

        //---- startYField ----
        startYField.setToolTipText("Y co\u00f6rdinate from upper left corner of screenshot area.");

        //---- endXLabel ----
        endXLabel.setText("End X: ");

        //---- endXField ----
        endXField.setToolTipText("X co\u00f6rdinate from bottom right corner of screenshot area.");

        //---- endYLabel ----
        endYLabel.setText("End Y: ");

        //---- endYField ----
        endYField.setToolTipText("Y co\u00f6rdinate from bottom right corner of screenshot area.");

        //---- keyCodeLabel ----
        keyCodeLabel.setText("Key Code: ");

        //---- keyCodeField ----
        keyCodeField.setToolTipText("Int value / Key code from key to use.");

        //---- buttonCodeLabel ----
        buttonCodeLabel.setText("Button Code: ");

        //---- captureFullScreenBox ----
        captureFullScreenBox.setText("Capture full screen: ");
        captureFullScreenBox.setToolTipText("Capture full screen instead of custom area.");

        //---- wheelAmtLabel ----
        wheelAmtLabel.setText("Wheel Amt: ");

        //---- wheelAmtField ----
        wheelAmtField.setToolTipText("Number of notches to move the mouse wheel. Neg value: Up / Pos value: Down");

        //---- getMousePosButton ----
        getMousePosButton.setText("Get mouse position (F3)");

        //---- xCoordMouse ----
        xCoordMouse.setToolTipText("X co\u00f6rdinate mouse position.");

        //---- yCoordMouse ----
        yCoordMouse.setToolTipText("Y co\u00f6rdinate mouse position.");

        //---- addActionButton ----
        addActionButton.setText("Add");

        //---- actionsLabel ----
        actionsLabel.setText("Actions: ");
        actionsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

        //---- autoDelayLabel ----
        autoDelayLabel.setText("Auto delay: ");

        //---- autoDelayField ----
        autoDelayField.setToolTipText("Auto delay between every action in ms.");

        //---- amountRepeatsLabel ----
        amountRepeatsLabel.setText("# of repeats: ");

        //======== actionsScrollPane ========
        {

            //---- actionsList ----
            actionsList.setToolTipText("Added actions.");
            actionsScrollPane.setViewportView(actionsList);
        }

        //---- deleteActionButton ----
        deleteActionButton.setText("Delete");

        //---- updateActionButton ----
        updateActionButton.setText("Update");

        //---- startButton ----
        startButton.setText("Start (F1)");

        //---- stopButton ----
        stopButton.setText("Stop (F2)");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(actionsScrollPane, GroupLayout.Alignment.TRAILING)
                                        .addComponent(addActionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(autoClickerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(addActionLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(actionComboBox))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(delayLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                                        .addComponent(xLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(delayField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                        .addComponent(xField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(yLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wheelAmtLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(yField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                        .addComponent(wheelAmtField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
                                        .addComponent(captureFullScreenBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(startXLabel, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                                        .addComponent(endXLabel, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(startXField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                        .addComponent(endXField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(endYLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(endYField, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(startYLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(startYField, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(getMousePosButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(xCoordMouse))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(keyCodeLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(keyCodeField, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(buttonCodeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(yCoordMouse, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonComboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(actionsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(autoDelayLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(autoDelayField, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(amountRepeatsLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(repeatField))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(startButton, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                        .addComponent(updateActionButton, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(stopButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(deleteActionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(autoClickerLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addActionLabel)
                                        .addComponent(actionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(delayLabel)
                                        .addComponent(delayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wheelAmtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wheelAmtLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(xLabel)
                                        .addComponent(xField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yLabel)
                                        .addComponent(yField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(captureFullScreenBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(startYField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startXLabel)
                                        .addComponent(startXField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startYLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(endXLabel)
                                        .addComponent(endYLabel)
                                        .addComponent(endYField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endXField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(keyCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonCodeLabel)
                                        .addComponent(keyCodeLabel)
                                        .addComponent(buttonComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(getMousePosButton)
                                        .addComponent(xCoordMouse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yCoordMouse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addActionButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actionsLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(autoDelayLabel)
                                        .addComponent(amountRepeatsLabel)
                                        .addComponent(repeatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(autoDelayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actionsScrollPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteActionButton)
                                        .addComponent(updateActionButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(startButton)
                                        .addComponent(stopButton))
                                .addContainerGap(2, Short.MAX_VALUE))
        );
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to set current os look and feel.");
        }

        pack();
        setLocationRelativeTo(getOwner());
        setResizable(false);
    }

    private void addFunctionGui() {

        enableOptions();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);

                System.exit(1);
            }
        });


        actionComboBox.addActionListener(e -> enableOptions());

        captureFullScreenBox.addActionListener(e -> {
            startXField.setEnabled(!captureFullScreenBox.isSelected());
            startYField.setEnabled(!captureFullScreenBox.isSelected());
            endXField.setEnabled(!captureFullScreenBox.isSelected());
            endYField.setEnabled(!captureFullScreenBox.isSelected());

            startXLabel.setEnabled(!captureFullScreenBox.isSelected());
            startYLabel.setEnabled(!captureFullScreenBox.isSelected());
            endXLabel.setEnabled(!captureFullScreenBox.isSelected());
            endYLabel.setEnabled(!captureFullScreenBox.isSelected());
        });

        getMousePosButton.addActionListener(e -> {
            Point mousePosition = MouseInfo.getPointerInfo().getLocation();

            xCoordMouse.setText(String.valueOf(mousePosition.x));
            yCoordMouse.setText(String.valueOf(mousePosition.y));
        });

        addActionButton.addActionListener(e -> {
            switch (actionComboBox.getSelectedIndex()) {
                case 0:

                    try {
                        autoClicker.addDelay(Integer.parseInt(delayField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Delay value", "Invalid delay value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 1:

                    try {
                        autoClicker.addMouseMove(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Mouse move value", "Invalid mouse move values", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 2:
                    autoClicker.addMouseClick(buttonComboBox.getItemAt(buttonComboBox.getSelectedIndex()).getButtonCode());
                    break;
                case 3:
                    autoClicker.addMousePress(buttonComboBox.getItemAt(buttonComboBox.getSelectedIndex()).getButtonCode());
                    break;
                case 4:
                    autoClicker.addMouseRelease(buttonComboBox.getItemAt(buttonComboBox.getSelectedIndex()).getButtonCode());
                    break;
                case 5:

                    try {
                        autoClicker.addMouseWheel(Integer.parseInt(wheelAmtField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Mouse wheel value", "Invalid mouse wheel value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 6:

                    try {
                        autoClicker.addKeyClick(Integer.parseInt(keyCodeField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Key code value", "Invalid key code value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 7:

                    try {
                        autoClicker.addKeyPress(Integer.parseInt(keyCodeField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Key code value", "Invalid key code value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 8:

                    try {
                        autoClicker.addKeyRelease(Integer.parseInt(keyCodeField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Key code value", "Invalid key code value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 9:

                    try {
                        if (captureFullScreenBox.isSelected()) {
                            autoClicker.addScreenCapture();
                        } else {
                            autoClicker.addScreenCapture(
                                    Integer.parseInt(startXField.getText()),
                                    Integer.parseInt(startYField.getText()),
                                    Integer.parseInt(endXField.getText()),
                                    Integer.parseInt(endYField.getText()));
                        }
                    } catch (Exception r) {
                        showJOptionPane("Screenshot values", "Invalid screenshot values", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
            }
            repaintActionList();
        });

        actionsList.addListSelectionListener(e -> {

            if (actionsList.getSelectedIndex() == -1) { //Nothing selected
                updateActionButton.setEnabled(false);
                deleteActionButton.setEnabled(false);
            } else {
                updateActionButton.setEnabled(true);
                deleteActionButton.setEnabled(true);


                actionComboBox.setSelectedIndex(addedActionListModel.getElementAt(actionsList.getSelectedIndex()).getOperationTypeEnum().getTypeId());
                enableOptions();
            }
        });

        deleteActionButton.addActionListener(e -> {
            autoClicker.getCommandList().remove(actionsList.getSelectedIndex());
            repaintActionList();
        });

        updateActionButton.addActionListener(e -> {
            switch (actionComboBox.getSelectedIndex()) {
                case 0:

                    try {
                        autoClicker.updateDelay(actionsList.getSelectedIndex(), Integer.parseInt(delayField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Delay value", "Invalid delay value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 1:

                    try {
                        autoClicker.updateMouseMove(actionsList.getSelectedIndex(), Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Mouse move value", "Invalid mouse move values", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 2:
                    autoClicker.updateMouseClick(actionsList.getSelectedIndex(), buttonComboBox.getItemAt(buttonComboBox.getSelectedIndex()).getButtonCode());
                    break;
                case 3:
                    autoClicker.updateMousePress(actionsList.getSelectedIndex(), buttonComboBox.getItemAt(buttonComboBox.getSelectedIndex()).getButtonCode());
                    break;
                case 4:
                    autoClicker.updateMouseRelease(actionsList.getSelectedIndex(), buttonComboBox.getItemAt(buttonComboBox.getSelectedIndex()).getButtonCode());
                    break;
                case 5:

                    try {
                        autoClicker.updateMouseWheel(actionsList.getSelectedIndex(), Integer.parseInt(wheelAmtField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Mouse wheel value", "Invalid mouse wheel value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 6:

                    try {
                        autoClicker.updateKeyClick(actionsList.getSelectedIndex(), Integer.parseInt(keyCodeField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Key code value", "Invalid key code value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 7:

                    try {
                        autoClicker.updateKeyPress(actionsList.getSelectedIndex(), Integer.parseInt(keyCodeField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Key code value", "Invalid key code value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 8:

                    try {
                        autoClicker.updateKeyRelease(actionsList.getSelectedIndex(), Integer.parseInt(keyCodeField.getText()));
                    } catch (Exception r) {
                        showJOptionPane("Key code value", "Invalid key code value", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case 9:

                    try {
                        if (captureFullScreenBox.isSelected()) {
                            autoClicker.updateScreenCapture(actionsList.getSelectedIndex());
                        } else {
                            autoClicker.updateScreenCapture(actionsList.getSelectedIndex(),
                                    Integer.parseInt(startXField.getText()),
                                    Integer.parseInt(startYField.getText()),
                                    Integer.parseInt(endXField.getText()),
                                    Integer.parseInt(endYField.getText()));
                        }
                    } catch (Exception r) {
                        showJOptionPane("Screenshot values", "Invalid screenshot values", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
            }
            repaintActionList();
        });

        startButton.addActionListener(e -> start());

        stopButton.addActionListener(e -> autoClicker.stopLooping());
    }

    private void enableOptions() {

        if (actionsList.getSelectedIndex() == -1) { //Nothing selected
            updateActionButton.setEnabled(false);
            deleteActionButton.setEnabled(false);
        } else {
            updateActionButton.setEnabled(true);
            deleteActionButton.setEnabled(true);
        }

        delayField.setEnabled(false);
        delayLabel.setEnabled(false);

        wheelAmtField.setEnabled(false);
        wheelAmtLabel.setEnabled(false);

        xField.setEnabled(false);
        xLabel.setEnabled(false);
        yField.setEnabled(false);
        yLabel.setEnabled(false);

        captureFullScreenBox.setEnabled(false);

        startXField.setEnabled(false);
        startYField.setEnabled(false);
        endXField.setEnabled(false);
        endYField.setEnabled(false);

        startXLabel.setEnabled(false);
        startYLabel.setEnabled(false);
        endXLabel.setEnabled(false);
        endYLabel.setEnabled(false);

        keyCodeField.setEnabled(false);
        keyCodeLabel.setEnabled(false);
        buttonComboBox.setEnabled(false);
        buttonCodeLabel.setEnabled(false);

        switch (actionComboBox.getSelectedIndex()) {
            case 0:
                delayField.setEnabled(true);
                delayLabel.setEnabled(true);
                break;
            case 1:
                xField.setEnabled(true);
                yField.setEnabled(true);

                xLabel.setEnabled(true);
                yLabel.setEnabled(true);
                break;
            case 2:
                buttonComboBox.setEnabled(true);
                buttonCodeLabel.setEnabled(true);
                break;
            case 3:
                buttonComboBox.setEnabled(true);
                buttonCodeLabel.setEnabled(true);
                break;
            case 4:
                buttonComboBox.setEnabled(true);
                buttonCodeLabel.setEnabled(true);
                break;
            case 5:
                wheelAmtField.setEnabled(true);
                wheelAmtLabel.setEnabled(true);
                break;
            case 6:
                keyCodeField.setEnabled(true);
                keyCodeLabel.setEnabled(true);
                break;
            case 7:
                keyCodeField.setEnabled(true);
                keyCodeLabel.setEnabled(true);
                break;
            case 8:
                keyCodeField.setEnabled(true);
                keyCodeLabel.setEnabled(true);
                break;
            case 9:
                captureFullScreenBox.setEnabled(true);
                captureFullScreenBox.setSelected(true);

                break;
        }
    }

    private void showJOptionPane(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    private void repaintActionList() {
        addedActionListModel.clear();

        try {
            for (Operation operation : autoClicker.getCommandList()) {
                addedActionListModel.addElement(operation);
            }
        }catch (NullPointerException ignore){}
    }

    public void selectListAction(int index) {
        int[] array = {index};
        actionsList.setSelectedIndices(array);
    }

    private void start() {

        try {
            autoClicker.start(this, Integer.parseInt(repeatField.getText()), Integer.parseInt(autoDelayField.getText()));
        } catch (Exception r) {
            showJOptionPane("Repeat or AutoDelay value", "Invalid repeat or autoDelay value", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMenuFunction() {
        exitMenuItem.addActionListener(e -> System.exit(1));
        saveMenuitem.addActionListener(e -> SaveData.saveData(this, autoClicker.getCommandList()));

        openMenuItem.addActionListener(e -> {
            autoClicker.setCommandList(SaveData.loadData(this));
            repaintActionList();
        });

        aboutMenuItem.addActionListener(e -> showJOptionPane("About", "Made by Maarten Boodts \nwww.blackshadow.be \nDecember 2015", JOptionPane.INFORMATION_MESSAGE));
        infoMenuItem.addActionListener(e -> showJOptionPane("Info", "Most should be explained if you hover over the boxes. \nKey code: values from KeyEvent from java.", JOptionPane.INFORMATION_MESSAGE));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F1) {
            start();
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F2) {
            autoClicker.stopLooping();
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F3) {
            Point mousePosition = MouseInfo.getPointerInfo().getLocation();

            xCoordMouse.setText(String.valueOf(mousePosition.x));
            yCoordMouse.setText(String.valueOf(mousePosition.y));
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }
}
