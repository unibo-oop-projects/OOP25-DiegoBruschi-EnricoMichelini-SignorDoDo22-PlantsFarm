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
    private static final int GRID_COLS = 3;
    private static final int GAP = 10;
    private static final int PADDING = 20;

    private static final Color BADGE_COMMON = new Color(60, 179, 113);
    private static final Color BADGE_RARE = new Color(138, 43, 226);
    private static final Color BADGE_EPIC = new Color(220, 20, 60);
    private static final Color BADGE_LEGENDARY = new Color(255, 165, 0);

    private static final Color BG = new Color(245, 245, 220);

    private static final int FONT_SIZE_TITLE = 30;
    private static final int FONT_SIZE_BADGE = 25;
    private static final int FONT_SIZE_DESCRIPTION = 15;

    private final JDialog encyclpediaScreen;
    private final JPanel buttonPanel;
    private final JLabel nameLabel;
    private final JLabel plantStageImageLabel;
    private final JLabel rarityLabel;
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

        final JPanel detailsPanel = new JPanel(new BorderLayout(GAP, GAP));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        detailsPanel.setBackground(BG);

        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, 0));
        topPanel.setOpaque(false);

        this.nameLabel = new JLabel("Select a Plant");
        this.nameLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_TITLE));

        this.stageButton = ButtonFactory.createButton("Next Stage");
        this.stageButton.setVisible(false);

        topPanel.add(this.nameLabel);
        topPanel.add(this.stageButton);

        final JPanel centerWrapper = new JPanel(new BorderLayout(0, GAP));
        centerWrapper.setOpaque(false);

        this.plantStageImageLabel = new JLabel();
        this.plantStageImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.rarityLabel = new JLabel("");
        this.rarityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.rarityLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_BADGE));
        this.rarityLabel.setOpaque(true);
        this.rarityLabel.setForeground(Color.WHITE);
        this.rarityLabel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.rarityLabel.setVisible(false);

        final JPanel rarityBadge = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rarityBadge.setOpaque(false);
        rarityBadge.add(this.rarityLabel);

        centerWrapper.add(this.plantStageImageLabel, BorderLayout.CENTER);
        centerWrapper.add(rarityBadge, BorderLayout.SOUTH);

        this.plantDescriptionArea = new JTextArea("Click on a plant icon.");
        this.plantDescriptionArea.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_DESCRIPTION));
        this.plantDescriptionArea.setEditable(false);
        this.plantDescriptionArea.setLineWrap(true);
        this.plantDescriptionArea.setOpaque(false);

        detailsPanel.add(topPanel, BorderLayout.NORTH);
        detailsPanel.add(centerWrapper, BorderLayout.CENTER);
        detailsPanel.add(this.plantDescriptionArea, BorderLayout.SOUTH);

        this.encyclpediaScreen.add(this.buttonPanel);
        this.encyclpediaScreen.add(detailsPanel);
    }

    /**
     * Updates rarity badge.
     *
     * @param rarity the name of the rarity.
     */
    public void setRarity(final String rarity) {
        this.rarityLabel.setVisible(true);
        switch (rarity) {
            case "COMMON":
                this.rarityLabel.setText("COMMON");
                this.rarityLabel.setBackground(BADGE_COMMON);
                break;
            case "RARE":
                this.rarityLabel.setText("RARE");
                this.rarityLabel.setBackground(BADGE_RARE);
                break;
            case "EPIC":
                this.rarityLabel.setText("EPIC");
                this.rarityLabel.setBackground(BADGE_EPIC);
                break;
            case "LEGENDARY":
                this.rarityLabel.setText("LEGENDARY");
                this.rarityLabel.setBackground(BADGE_LEGENDARY);
                break;
            default:
                this.rarityLabel.setVisible(false);
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
