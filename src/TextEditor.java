
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JFrame {

    private JScrollPane areaScrollPane;
    private JTextArea textArea;
    private JTextField textField;
    private JPanel optionsPanel;
    private JButton saveFileB;
    private JButton openFileB;
    private BorderLayout borderLayout;

    private String stageTitle = "The third stage";
    private String nameTextArea = "TextArea";
    private String nameTextField = "FilenameField";
    private String nameButtonSave = "SaveButton";
    private String nameButtonLoad = "LoadButton";
    private String nameScrollPane = "ScrollPane";
    private String nameMenuTabFile = "MenuFile";
    private String nameMenuItemLoad = "MenuLoad";
    private String nameMenuItemSave = "MenuSave";
    private String nameMenuItemExit = "MenuExit";


    private int frameH = 550;
    private int frameW = 400;

    public TextEditor() {

        initComponents();
        setVisible(true);

    }

    private void initComponents() {
        setTitle(stageTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setSize(frameW, frameH);
        initTextArea();
        optionsPanel = new JPanel();
        initOptionsPanel();
        initMenuBar();
    }

    public void initTextArea(){
        textArea = new JTextArea();
        textArea.setName(nameTextArea);
        textArea.setMargin(new Insets(10,10,10,10));
        textArea.setBounds(10,45,frameW - 20,frameH - 20);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);

        areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setName(nameScrollPane);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5,5,5,5),
                        areaScrollPane.getBorder()));
        add(areaScrollPane, BorderLayout.CENTER);
    }

    public void initOptionsPanel() {
        Dimension sizeOptionsPanel = new Dimension(frameW - 30, 45);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.setMinimumSize(sizeOptionsPanel);
        optionsPanel.setMaximumSize(sizeOptionsPanel);
        optionsPanel.setPreferredSize(sizeOptionsPanel);

        optionsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(10,5,10,5),
                        optionsPanel.getBorder()));

        textField = new JTextField();
        textField.setColumns(20);
        textField.setSize(100,30);
        saveFileB = new JButton("Save");
        openFileB = new JButton("Load");

        textField.setName(nameTextField);
        saveFileB.setName(nameButtonSave);
        openFileB.setName(nameButtonLoad);

        saveFileB.addActionListener(saveFileListener);
        openFileB.addActionListener(openFileListener);
        optionsPanel.add(textField);
        optionsPanel.add(openFileB);
        optionsPanel.add(saveFileB);

        add(optionsPanel, BorderLayout.NORTH);
    }


    ActionListener openFileListener = (actionEvent) -> {

        String userInput = textField.getText();
        File f;
        String fileContent;
        if ((f = new File(userInput)).isFile()) {
            try {
                fileContent = Files.readString(Paths.get(userInput));
                textArea.setText(fileContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            textArea.setText("");
        }
    };

    ActionListener saveFileListener = (actionEvent) -> {
        String userInput = textField.getText();
        File file = new File(userInput);

        try {
            boolean isCreated = file.createNewFile();
        } catch (IOException e){

        }
        try (PrintWriter p = new PrintWriter(new FileOutputStream(file, false))){
            String toSave = textArea.getText();
            p.print(toSave);
        } catch (IOException e) {

        }
    };

    ActionListener exitListener = (actionEvent) -> {
        dispose();
        //System.exit(0);
    };
    
    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setName(nameMenuTabFile);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);


        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        loadMenuItem.setName(nameMenuItemLoad);
        saveMenuItem.setName(nameMenuItemSave);
        exitMenuItem.setName(nameMenuItemExit);

        loadMenuItem.addActionListener(openFileListener);
        saveMenuItem.addActionListener(saveFileListener);
        exitMenuItem.addActionListener(exitListener);

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
    }
}
