package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Terminal {@link DrawNumberView} implementation.
 */
public class DrawNumberStandardOutputView implements DrawNumberView {

    @Override
    public void setController(DrawNumberController observer) {
        /*
         * output only
         */
    }

    @Override
    public void start() {
    }

    @Override
    public void result(DrawResult res) {
        System.out.println(res.getDescription());
    }

}
