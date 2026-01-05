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

public class ShopMenuFrame {

    private static final double FRAME_WIDTH_RATIO = 0.70;
    private static final double FRAME_HEIGHT_RATIO = 0.75;
    private static final double BUTTON_GAP_RATIO = 0.01;

    private final JFrame parentFrame;
    private JFrame frame;

    //Components
    private JButton sellButton1;
    private JButton getPlantButton;
    private JButton sellButton2;
    private JPanel buttonPanel;

    public ShopMenuFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        createUserInterface();
    }

    private void createUserInterface() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (screenSize.getWidth() * FRAME_WIDTH_RATIO);
        final int height = (int) (screenSize.getHeight() * FRAME_HEIGHT_RATIO);

        frame = new JFrame("Shop Menu");
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(parentFrame);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));

        final int gap = (int) (screenSize.getWidth() * BUTTON_GAP_RATIO);
        
        final int vPadding = (int) (height * 0.25);
        final int hPadding = (int) (width * 0.02);

        buttonPanel = new JPanel(new GridLayout(1, 3, gap, gap));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(vPadding, hPadding, vPadding, hPadding));

        sellButton1 = new JButton("SELL PLANTS");
        sellButton1.setHorizontalTextPosition(SwingConstants.CENTER);

        sellButton2 = new JButton("SELL PLANTS");
        sellButton2.setHorizontalTextPosition(SwingConstants.CENTER);

        getPlantButton = new JButton("GET PLANT");
        getPlantButton.setHorizontalTextPosition(SwingConstants.CENTER);

        buttonPanel.add(sellButton1);
        buttonPanel.add(sellButton2);
        buttonPanel.add(getPlantButton);

        frame.add(buttonPanel);
    }

    //Applications
    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public void addSellListener(java.awt.event.ActionListener l) {
        sellButton1.addActionListener(l);
        sellButton2.addActionListener(l);
    }

    public void getPlantListener(java.awt.event.ActionListener l) {
        getPlantButton.addActionListener(l);
    }

    public void setSellButtonContent(int index, String text, Icon icon) {
        if (index == 1) {
            sellButton1.setText(text);
            sellButton1.setIcon(icon);
            sellButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
            sellButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        } else if (index == 2) {
            sellButton2.setText(text);
            sellButton2.setIcon(icon);
            sellButton2.setVerticalTextPosition(SwingConstants.BOTTOM);
            sellButton2.setHorizontalTextPosition(SwingConstants.CENTER);
        }
    }

}
