package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Manages the animation for opening a Mystery Box.
 */
public final class MysteryBox {

    private static final int CLOCK = 100;
    private static final int FRAMES = 20;

    private static final String FONT = "Arial";
    private static final int TITLE_SIZE = 40;
    private static final int NAME_SIZE = 30;
    private static final int WINNER_SIZE = 45;
    private static final int BUTTON_SIZE = 30;
    private static final int PADDING = 40;
    private static final int GAP = 20;

    private static final Color BG_COMMON = new Color(144, 238, 144);
    private static final Color BG_RARE = new Color(221, 160, 221);
    private static final Color BG_EPIC = new Color(255, 117, 120);
    private static final Color BG_LEGENDARY = new Color(255, 252, 115);

    private static final Color DARK_COMMON = new Color(34, 139, 34);
    private static final Color DARK_RARE = new Color(138, 43, 226);
    private static final Color DARK_EPIC = new Color(220, 20, 60);
    private static final Color DARK_LEGENDARY = new Color(255, 140, 0);

    private final JDialog dialog;
    private final JLabel titleLabel;
    private final JLabel iconLabel;
    private final JLabel nameLabel;
    private final JButton getWinnerButton;
    private final Random random;

    private final PlantType unlockedPlant;
    private final List<PlantType> frames;
    private final Timer animationTimer;
    private int currentIndex;

    /**
     * Creates a new Mystery Box opening.
     *
     * @param unlockedPlant The winner plant that the user has won.
     */
    public MysteryBox(final PlantType unlockedPlant) {
        this.unlockedPlant = unlockedPlant;
        this.random = new Random();
        this.frames = generateFramesList();

        this.dialog = new JDialog((JFrame) null, "Mystery Box", true);
        this.dialog.setUndecorated(true);
        this.dialog.setResizable(false);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.dialog.setSize(screenSize);
        this.dialog.setLocationRelativeTo(null);

        final JPanel mainPanel = new JPanel(new BorderLayout());
        Color tempDark = Color.BLACK;

        switch (unlockedPlant.getRarity()) {
            case COMMON -> {
                mainPanel.setBackground(BG_COMMON);
                tempDark = DARK_COMMON;
            }
            case RARE -> {
                mainPanel.setBackground(BG_RARE);
                tempDark = DARK_RARE;
            }
            case EPIC -> {
                mainPanel.setBackground(BG_EPIC);
                tempDark = DARK_EPIC;
            }
            case LEGENDARY -> {
                mainPanel.setBackground(BG_LEGENDARY);
                tempDark = DARK_LEGENDARY;
            }
        }
        mainPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        final Color darkColor = tempDark;

        this.titleLabel = new JLabel("OPENING MYSTERY BOX...", SwingConstants.CENTER);
        this.titleLabel.setFont(new Font(FONT, Font.BOLD, TITLE_SIZE));
        this.titleLabel.setForeground(darkColor);

        mainPanel.add(this.titleLabel, BorderLayout.NORTH);

        this.iconLabel = new JLabel("", SwingConstants.CENTER);
        this.iconLabel.setIcon(Texture.getMysteryPlantIcon(frames.get(0).getName()));

        mainPanel.add(this.iconLabel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 0, GAP));
        bottomPanel.setOpaque(false);

        this.nameLabel = new JLabel("???", SwingConstants.CENTER);
        this.nameLabel.setFont(new Font(FONT, Font.ITALIC, NAME_SIZE));
        this.nameLabel.setForeground(darkColor);

        final JPanel buttonWrapper = new JPanel();
        buttonWrapper.setOpaque(false);

        this.getWinnerButton = ButtonFactory.createButton("GET!");
        this.getWinnerButton.setFont(new Font(FONT, Font.BOLD, BUTTON_SIZE));
        this.getWinnerButton.setForeground(darkColor);
        this.getWinnerButton.setVisible(false);
        this.getWinnerButton.addActionListener(e -> {
            this.dialog.dispose();
        });

        buttonWrapper.add(this.getWinnerButton);

        bottomPanel.add(this.nameLabel);
        bottomPanel.add(buttonWrapper);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.dialog.add(mainPanel);

        this.animationTimer = new Timer(CLOCK, e -> updateFrame());
    }

    private List<PlantType> generateFramesList() {
        final List<PlantType> sequence = new ArrayList<>();
        final PlantType[] allPlants = PlantType.values();

        for (int i = 0; i < FRAMES; i++) {
            sequence.add(allPlants[this.random.nextInt(allPlants.length)]);
        }
        sequence.add(this.unlockedPlant);
        return sequence;
    }

    private void updateFrame() {
        if (currentIndex < frames.size() - 1) {
            final PlantType current = frames.get(currentIndex);
            this.iconLabel.setIcon(Texture.getMysteryPlantIcon(current.getName()));
            currentIndex++;
        } else {
            this.animationTimer.stop();
            showWinner();
        }
    }

    private void showWinner() {
        final String realName = this.unlockedPlant.getName();
        this.iconLabel.setIcon(Texture.getMysteryPlantIcon(realName));

        this.titleLabel.setText("NEW PLANT UNLOCKED!");

        this.nameLabel.setText(realName);
        this.nameLabel.setFont(new Font(FONT, Font.BOLD, WINNER_SIZE));

        this.getWinnerButton.setVisible(true);
        this.getWinnerButton.requestFocusInWindow();
    }

    /**
     * Starts the animation and shows the dialog.
     */
    public void start() {
        this.animationTimer.start();
        this.dialog.setVisible(true);
    }
}
