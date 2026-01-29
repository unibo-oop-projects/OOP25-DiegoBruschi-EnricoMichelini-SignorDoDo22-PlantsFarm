package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.view.menu.PauseMenuScreen;

import javax.swing.JOptionPane;

/**
 * Controller responsible for managing the Pause Menu interactions.
 */
public final class PauseMenuController {

    private final PauseMenuScreen view;
    private final Runnable onCloseMainScreen;
    private final Runnable onGameReset;

    /**
     * Creates a new PauseMenuController.
     *
     * @param onCloseMainScreen The action to run when the game is exited entirely.
     * @param onGameReset       The action to run when the player confirms a reset.
     */
    public PauseMenuController(final Runnable onCloseMainScreen, final Runnable onGameReset) {
        this.view = new PauseMenuScreen();
        this.onCloseMainScreen = onCloseMainScreen;
        this.onGameReset = onGameReset;
        setupListeners();
    }

    private void setupListeners() {
        this.view.setResumeButton(e -> this.view.close());

        this.view.setCommandsButton(e -> {
            final String commands = """
                A -> sinistra
                W -> alto
                S -> basso
                D -> destra
                E -> annaffiatoio
                """;
            JOptionPane.showMessageDialog(null, commands, "Commands", JOptionPane.INFORMATION_MESSAGE);
        });

        this.view.setResetButton(e -> {
            final int choice = JOptionPane.showConfirmDialog(
                null,
                "Confirm reset of game data?",
                "Reset Game",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                if (onGameReset != null) {
                    onGameReset.run();
                }
                JOptionPane.showMessageDialog(null,
                    "Game data reset successfully.",
                    "Reset Complete",
                    JOptionPane.INFORMATION_MESSAGE);
                this.view.close();
                if (onCloseMainScreen != null) {
                    onCloseMainScreen.run();
                }
            }
        });

        this.view.setExtraButton(e -> {
            final String message = "Plants Farm created by\nEnrico Michelini - Diego Bruschi - Francesco Fusillo";
            JOptionPane.showMessageDialog(null, message, "Extra Info", JOptionPane.INFORMATION_MESSAGE);
        });

        this.view.setCreditsButton(e -> {
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
