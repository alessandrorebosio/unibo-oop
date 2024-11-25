package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import java.util.List;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() {
    }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException    if the fetches class does not exist
     * @throws NoSuchMethodException     if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException    if the constructor throws exceptions
     * @throws IllegalAccessException    in case of reflection issues
     * @throws IllegalArgumentException  in case of reflection issues
     * @throws SecurityException
     */
    public static void main(final String... args) throws ClassNotFoundException, NoSuchMethodError,
            InvocationTargetException, InstantiationError, IllegalAccessError, IllegalArgumentException,
            InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {

        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        for (final var viewType : List.of("StandardOutput", "Swing")) {
            final var newView = Class.forName("it.unibo.mvc.view.DrawNumber" + viewType + "View").getConstructor()
                    .newInstance();
            try {
                app.addView((DrawNumberView) newView);
            } catch (IllegalStateException e) {
                throw new IllegalStateException(newView.getClass() + " is not a subclass of " + DrawNumberView.class);

            }
        }
    }
}
