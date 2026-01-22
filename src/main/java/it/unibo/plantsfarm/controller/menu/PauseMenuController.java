package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.view.menu.PauseMenuScreen;

import javax.swing.JOptionPane;

/**
 * Controller responsible for managing the Pause Menu interactions.
 */
public final class PauseMenuController {

    private final PauseMenuScreen view;
    private final Runnable onCloseMainScreen;

    /**
     * Creates a new PauseMenuController.
     *
     * @param onCloseMainScreen The action to run when the game is exited entirely.
     */
    public PauseMenuController(final Runnable onCloseMainScreen) {
        this.view = new PauseMenuScreen();
        this.onCloseMainScreen = onCloseMainScreen;
        setupListeners();
    }

    private void setupListeners() {
        this.view.setResumeButton(e -> this.view.close());

        this.view.setExtraButton(e -> {
            final String message = "Plants Farm created by\nEnrico Michelini - Diego Bruschi - Francesco Fusillo";
            JOptionPane.showMessageDialog(null, message, "Extra Info", JOptionPane.INFORMATION_MESSAGE);
        });

        this.view.setExitButton(e -> {
            this.view.close();
            if (onCloseMainScreen != null) {
                onCloseMainScreen.run();
            }
        });
    }

    /**
     * Shows the pause menu.
     */
    public void start() {
        this.view.show();
    }
}
