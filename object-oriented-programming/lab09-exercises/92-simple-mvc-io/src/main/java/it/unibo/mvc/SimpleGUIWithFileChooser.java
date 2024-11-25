package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A simple GUI with a file chooser.
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My Second Java GUI");
    private static final int PROPORTION = 3;

    /**
     * Builds a new {@link SimpleGUIWithFileChooser}.
     * 
     * @param controller the controller
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel = GUIUtils.createBasicPanel(frame, controller);

        final JTextField path = new JTextField(controller.getPath());
        path.setEditable(false);

        final JButton bBrowse = new JButton("Browse...");
        bBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser("Choose new destination...");
                chooser.setSelectedFile(controller.getCurrentFile());
                final int result = chooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    final File newDest = chooser.getSelectedFile();
                    controller.setDestination(newDest);
                    path.setText(newDest.getPath());
                }
            }
        });

        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(path, BorderLayout.CENTER);
        topPanel.add(bBrowse, BorderLayout.LINE_END);

        panel.add(topPanel, BorderLayout.NORTH);
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
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
