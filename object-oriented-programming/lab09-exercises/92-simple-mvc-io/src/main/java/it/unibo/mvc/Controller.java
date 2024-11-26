package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILE = "output.txt";

    private File current = new File(HOME + File.separator + DEFAULT_FILE);

    /**
     * Sets a new destination file.
     *
     * @param file
     *             the file where to write
     */
    public void setDestination(final String file) {
        setDestination(new File(file));
    }

    /**
     * Sets a new destination file.
     *
     * @param file
     *             the file where to write
     */
    public void setDestination(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.current = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    /**
     * Returns the current file.
     * 
     * @return the current file
     */
    File getCurrentFile() {
        return current;
    }

    /**
     * Return the path of current file.
     * 
     * @return the current file path
     */
    String getPath() {
        return current.getPath();
    }

    /**
     * Saves some text on the designed file.
     * 
     * @param text
     *             the text to save
     * @throws IOException
     *                     if the writing fails
     */
    public void save(final String text) throws IOException {
        try (PrintStream out = new PrintStream(this.current, StandardCharsets.UTF_8)) {
            out.println(text);
        }
    }

}
