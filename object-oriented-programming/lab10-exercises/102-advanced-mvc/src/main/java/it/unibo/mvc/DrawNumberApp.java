package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *              the views to attach
     */
    public DrawNumberApp(final String config, final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view : views) {
            view.setObserver(this);
            view.start();
        }
        final Configuration.Builder configurationBuilder = new Configuration.Builder();
        try (final var contents = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(config)))) {
            for (var configLine = contents.readLine(); configLine != null; configLine = contents.readLine()) {
                var keyValue = configLine.split(":");
                if (keyValue.length == 2) {
                    var value = Integer.parseInt(keyValue[1].trim());
                    if (keyValue[0].contains("max")) {
                        configurationBuilder.setMax(value);
                    } else if (keyValue[0].contains("min")) {
                        configurationBuilder.setMin(value);
                    } else if (keyValue[0].contains("attempts")) {
                        configurationBuilder.setAttempts(value);
                    }
                } else {
                    displayError("I can't understand");
                }
            }
        } catch (IOException | NumberFormatException e) {
            displayError(e.getMessage());
        }
        final Configuration configuration = configurationBuilder.build();
        if (configuration.isConsistent()) {
            this.model = new DrawNumberImpl(configuration);
        } else {
            displayError("inconsistent");
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }

    private void displayError(final String err) {
        for (final DrawNumberView view : views) {
            view.displayError(err);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *             ignored
     * @throws FileNotFoundException
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp("config.yml", new DrawNumberViewImpl(), new PrintStreamView(System.out));
    }
}
