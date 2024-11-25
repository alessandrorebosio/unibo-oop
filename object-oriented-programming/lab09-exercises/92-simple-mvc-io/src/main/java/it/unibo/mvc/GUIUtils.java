package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Utility class for common GUI components and configuration.
 */
public final class GUIUtils {

    private GUIUtils() {
        // Prevent instantiation
    }

    /**
     * Configures the frame size based on the screen resolution and proportion.
     * 
     * @param frame      the frame to configure
     * @param proportion the proportion to divide the screen resolution
     */
    public static void configureFrameSize(final JFrame frame, final int proportion) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screen.width / proportion, screen.height / proportion);
    }

    /**
     * Creates a basic GUI panel with a save button and a text area.
     * 
     * @param frame      the frame to configure
     * @param controller the controller for the save action
     * @return the configured panel
     */
    public static JPanel createBasicPanel(final JFrame frame, final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel(new BorderLayout());
        final JButton bSave = new JButton("Save");
        final JTextArea text = new JTextArea();

        bSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.save(text.getText());
                } catch (final IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "An error occurred",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(bSave, BorderLayout.SOUTH);
        panel.add(text, BorderLayout.CENTER);
        return panel;
    }
}
