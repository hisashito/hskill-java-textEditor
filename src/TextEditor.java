import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor extends JFrame { //implements DocumentListener {

    private String stageTitle = "The first stage";
    private String textAreaName = "TextArea";
    private JScrollPane areaScrollPane;
    private JTextArea textArea;
    private JTextField textField;
    private BorderLayout borderLayout;
    JPanel optionsPanel;
    private JButton saveFileB;
    private JButton openFileB;

    private int frameH = 600;
    private int frameW = 450;



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
    }

    public void initTextArea(){

        textArea = new JTextArea();
        textArea.setName(textAreaName);
        textArea.setMargin(new Insets(10,10,10,10));
        textArea.setBounds(10,10,frameW - 20,frameH - 20);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);

        areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5,5,5,5),
                        areaScrollPane.getBorder()));
        add(areaScrollPane, BorderLayout.CENTER);
    }

    public void initOptionsPanel() {
        Dimension sizeOptionsPanel = new Dimension(frameW - 30, 30);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.setMinimumSize(sizeOptionsPanel);
        optionsPanel.setMaximumSize(sizeOptionsPanel);
        optionsPanel.setPreferredSize(sizeOptionsPanel);

        optionsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(4,4,4,4),
                        optionsPanel.getBorder()));

        textField = new JTextField();
        textField.setColumns(20);
        textField.setSize(100,30);
        saveFileB = new JButton("save");
        openFileB = new JButton("open");
        optionsPanel.add(textField);
        optionsPanel.add(openFileB);
        optionsPanel.add(saveFileB);

        add(optionsPanel, BorderLayout.NORTH);
    }


    ActionListener openFileListener = (actionEvent) -> {};
    ActionListener saveFileListener = (actionEvent) -> {};

    public static void setMargin(JComponent aComponent, int aTop,
                                 int aRight, int aBottom, int aLeft) {

        Border border = aComponent.getBorder();

        Border marginBorder = new EmptyBorder(new Insets(aTop, aLeft,
                aBottom, aRight));//from   www. java2s.com
        aComponent.setBorder(border == null ? marginBorder
                : new CompoundBorder(marginBorder, border));
    }
}
