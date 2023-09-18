import javax.swing.SwingUtilities;

public class ApplicationRunner {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}
