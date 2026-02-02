package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.TextureUtils;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Manages the view for the Encyclopedia feature.
 */
public final class EncyclopediaScreen {

    private static final String TITLE = "Encyclopedia";
    private static final String FONT_NAME = "Arial";
    private static final int GRID_COLS = 5;
    private static final int GAP = 5;
    private static final int PADDING = 10;

    private static final Color BG_COMMON = new Color(144, 238, 144);
    private static final Color BG_RARE = new Color(221, 160, 221);
    private static final Color BG_EPIC = new Color(255, 117, 120);
    private static final Color BG_LEGENDARY = new Color(255, 252, 115);

    private static final Color DARK_COMMON = new Color(34, 139, 34);
    private static final Color DARK_RARE = new Color(138, 43, 226);
    private static final Color DARK_EPIC = new Color(220, 20, 60);
    private static final Color DARK_LEGENDARY = new Color(255, 140, 0);

    private static final Color BG = new Color(245, 245, 220);

    private static final int FONT_SIZE_TITLE = 25;
    private static final int FONT_SIZE_DESCRIPTION = 15;

    private final JDialog encyclpediaScreen;
    private final JPanel buttonPanel;
    private final JPanel detailsPanel;
    private final JLabel nameLabel;
    private final JLabel plantStageImageLabel;
    private final JTextArea plantDescriptionArea;
    private final JButton stageButton;

    /**
     * Initializes the screen.
     */
    public EncyclopediaScreen() {
        this.encyclpediaScreen = WindowFactory.createMenuWindow(TITLE);
        this.encyclpediaScreen.setLayout(new GridLayout(1, 2));

        this.buttonPanel = new JPanel(new GridLayout(0, GRID_COLS, GAP, GAP));
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        this.detailsPanel = new JPanel(new BorderLayout(GAP, GAP));
        this.detailsPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.detailsPanel.setBackground(BG);

        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, 0));
        topPanel.setOpaque(false);

        this.nameLabel = new JLabel("Select a Plant");
        this.nameLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_TITLE));
        this.nameLabel.setOpaque(true);
        this.nameLabel.setBackground(BG);
        this.nameLabel.setForeground(Color.BLACK);
        this.nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.stageButton = ButtonFactory.createButton("Next Stage");
        this.stageButton.setVisible(false);

        topPanel.add(this.nameLabel);
        topPanel.add(this.stageButton);

        final JPanel centerWrapper = new JPanel(new BorderLayout(0, GAP));
        centerWrapper.setOpaque(false);

        this.plantStageImageLabel = new JLabel();
        this.plantStageImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        centerWrapper.add(this.plantStageImageLabel, BorderLayout.CENTER);

        this.plantDescriptionArea = new JTextArea("Click on a plant icon.");
        this.plantDescriptionArea.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_DESCRIPTION));
        this.plantDescriptionArea.setEditable(false);
        this.plantDescriptionArea.setLineWrap(true);
        this.plantDescriptionArea.setOpaque(false);

        this.detailsPanel.add(topPanel, BorderLayout.NORTH);
        this.detailsPanel.add(centerWrapper, BorderLayout.CENTER);
        this.detailsPanel.add(this.plantDescriptionArea, BorderLayout.SOUTH);

        this.encyclpediaScreen.add(this.buttonPanel);
        this.encyclpediaScreen.add(this.detailsPanel);
    }

    /**
     * Updates rarity badge.
     *
     * @param rarity the name of the rarity.
     */
    public void setRarity(final String rarity) {
        this.nameLabel.setForeground(Color.WHITE);
        switch (rarity) {
            case "COMMON":
                this.nameLabel.setBackground(DARK_COMMON);
                this.detailsPanel.setBackground(BG_COMMON);
                break;
            case "RARE":
                this.nameLabel.setBackground(DARK_RARE);
                this.detailsPanel.setBackground(BG_RARE);
                break;
            case "EPIC":
                this.nameLabel.setBackground(DARK_EPIC);
                this.detailsPanel.setBackground(BG_EPIC);
                break;
            case "LEGENDARY":
                this.nameLabel.setBackground(DARK_LEGENDARY);
                this.detailsPanel.setBackground(BG_LEGENDARY);
                break;
            default:
                this.nameLabel.setBackground(BG);
                this.nameLabel.setForeground(Color.BLACK);
                this.detailsPanel.setBackground(BG);
                break;
        }
    }

    /**
     * Shows the screen.
     *
     * @param names names list.
     * @param unlocked unlock status list.
     * @param listener controller listener.
     * @param stageCommand the stage command.
     */
    public void show(final List<String> names, final List<Boolean> unlocked,
        final ActionListener listener, final String stageCommand) {

        this.buttonPanel.removeAll();
        this.stageButton.setActionCommand(stageCommand);
        for (final ActionListener oldActionListener : this.stageButton.getActionListeners()) {
            this.stageButton.removeActionListener(oldActionListener);
        }
        this.stageButton.addActionListener(listener);

        for (int currentIndex = 0; currentIndex < names.size(); currentIndex++) {
            final String name = names.get(currentIndex);
            ImageIcon icon = Texture.getPlantIcon(name);

            if (!unlocked.get(currentIndex)) {
                icon = TextureUtils.createLockedIcon(icon);
            }

            final JButton button = ButtonFactory.createMenuButton(icon);

            if (unlocked.get(currentIndex)) {
                button.setActionCommand(name);
                button.addActionListener(listener);
            }

            this.buttonPanel.add(button);
        }
        this.encyclpediaScreen.setVisible(true);
    }

    /**
     * Updates image and returns true if successful.
     *
     * @param name plant name.
     * @param stage stage index.
     * @return true if icon was found, false otherwise.
     */
    public boolean updateStageImage(final String name, final int stage) {
        final ImageIcon icon = Texture.getPlantStageIcon(name, stage);
        if (icon != null) {
            this.plantStageImageLabel.setIcon(icon);
            return true;
        }
        return false;
    }

    /**
     * Updates details.
     *
     * @param name plant name.
     * @param description plant description.
     */
    public void updateDetails(final String name, final String description) {
        this.nameLabel.setText(name);
        this.plantDescriptionArea.setText(description);
        this.stageButton.setVisible(true);
    }
}
