package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();
    private final SimpleController controller;

    /**
     * builds a new {@link SimpleGui}.
     * 
     * @param controller controller the controller to use.
     */
    @SuppressFBWarnings(justification = "The controller is made to be used like this.")
    public SimpleGUI(final SimpleController controller) {
        this.controller = controller;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel topPanel = new JPanel(new BorderLayout());
        final JPanel centerPanel = new JPanel(new BorderLayout());
        final JPanel bottomPanel = new JPanel(new BorderLayout());

        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        final JButton print = new JButton("Print");

        final JButton showHistory = new JButton("Show history");
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.controller.setNextString(textField.getText());
                SimpleGUI.this.controller.printCurretString();
            }
        });
        showHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textArea.setText("");
                for (final var line : SimpleGUI.this.controller.history()) {
                    textArea.append(line + "\n");
                }
            }

        });

        topPanel.add(textField);
        bottomPanel.add(print);
        bottomPanel.add(showHistory, BorderLayout.LINE_END);

        centerPanel.add(textArea, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(centerPanel);

        /*
         * Make the frame one fifth the resolution of the screen. This very
         * method is enough for a single screen setup. In case of multiple
         * monitors, the primary is selected. In order to deal coherently with
         * multimonitor setups, other facilities exist (see the Java
         * documentation about this issue). It is MUCH better than manually
         * specify the size of a window in pixel: it takes into account the
         * current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
    }

    private void display() {
        frame.setVisible(true);
    }

    /**
     * @param args
     *             ignored.
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
