package it.unibo.mvc;

import java.util.List;

/**
 * A Controller holder.
 */
public interface Controller {

    /**
     * Set the next string to print.
     * 
     * @param nextString
     * 
     * @throws NullPointerException if the param is null
     */
    void setNextString(String nextString);

    /**
     * 
     * @return get the next string to print.
     */
    String getNextString();

    /**
     * 
     * @return the whole history.
     */
    List<String> history();

    /**
     * Print the current string.
     */
    void printCurretString();
}
