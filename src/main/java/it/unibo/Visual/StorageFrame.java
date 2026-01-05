package it.unibo.Visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

public class StorageFrame {

    private static final double FRAME_WIDTH_RATIO = 0.50;
    private static final double FRAME_HEIGHT_RATIO = 0.75;
    private static final double GAP_RATIO = 0.01;

    private final JFrame parentFrame;
    private JFrame frame;
    private JPanel itemsPanel;
    
    private final Map<String, JButton> itemButtons = new HashMap<>();

    public StorageFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        createUserInterface();
    }

    private void createUserInterface() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (screenSize.getWidth() * FRAME_WIDTH_RATIO);
        final int height = (int) (screenSize.getHeight() * FRAME_HEIGHT_RATIO);

        frame = new JFrame("Storage");
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(parentFrame);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        final int gap = (int) (screenSize.getWidth() * GAP_RATIO);

        itemsPanel = new JPanel(new GridLayout(6, 3, gap, gap));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));

        frame.add(itemsPanel, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public void createStorageSlot(String displayName, Icon icon) {
        String buttonText = formatButtonText(displayName, 0);
        
        JButton itemButton = new JButton(buttonText, icon);
        
        itemButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        itemButton.setVerticalTextPosition(SwingConstants.CENTER);
        itemButton.setHorizontalAlignment(SwingConstants.LEFT);
        
        itemButton.setFocusPainted(false);
        
        itemButtons.put(displayName, itemButton);
        itemsPanel.add(itemButton);
    }

    public void updateItemQuantity(String displayName, int newQuantity) {
        JButton btn = itemButtons.get(displayName);
        if (btn != null) {
            btn.setText(formatButtonText(displayName, newQuantity));
        }
    }

    private String formatButtonText(String name, int quantity) {
        return "<html><b>" + name + "</b><br>x " + quantity + "</html>";
    }
}