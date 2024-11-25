package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My First Java GUI");
    private static final int PROPORTION = 5;

    /**
     * Builds a new {@link SimpleGUI}.
     * 
     * @param controller the controller
     */
    public SimpleGUI(final Controller controller) {
        final JPanel panel = GUIUtils.createBasicPanel(frame, controller);
        frame.setContentPane(panel);
        GUIUtils.configureFrameSize(frame, PROPORTION);
    }

    private void display() {
        frame.setVisible(true);
    }

    /**
     * @param args ignored
     */
    public static void main(final String[] args) {
        new SimpleGUI(new Controller()).display();
    }
}
