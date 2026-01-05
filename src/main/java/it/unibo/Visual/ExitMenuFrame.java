package it.unibo.Visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ExitMenuFrame {

    private static final double FRAME_WIDTH_RATIO = 0.30;
    private static final double FRAME_HEIGHT_RATIO = 0.35;
    private static final double BUTTON_GAP_RATIO = 0.01;

    private final JFrame parentFrame;
    private JFrame frame;

    //Components
    private JButton resumeButton;
    private JButton exitButton;
    private JButton extraButton;
    private JPanel buttonPanel;

    public ExitMenuFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        createUserInterface();
    }

    private void createUserInterface() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (screenSize.getWidth() * FRAME_WIDTH_RATIO);
        final int height = (int) (screenSize.getHeight() * FRAME_HEIGHT_RATIO);

        frame = new JFrame("Exit Menu");
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(parentFrame);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));

        final int gap = (int) (screenSize.getHeight() * BUTTON_GAP_RATIO);
        
        final int vPadding = (int) (height * 0.05);
        final int hPadding = (int) (width * 0.10);

        buttonPanel = new JPanel(new GridLayout(3, 1, gap, gap));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(vPadding, hPadding, vPadding, hPadding));

        resumeButton = new JButton("RESUME");
        resumeButton.setHorizontalTextPosition(SwingConstants.CENTER);

        extraButton = new JButton("EXTRA");
        extraButton.setHorizontalTextPosition(SwingConstants.CENTER);

        exitButton = new JButton("EXIT");
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);

        buttonPanel.add(resumeButton);
        buttonPanel.add(extraButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel);
    }

    //Applications
    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public void addResumeListener(java.awt.event.ActionListener l) {
        resumeButton.addActionListener(l);
    }

    public void addExitListener(java.awt.event.ActionListener l) {
        exitButton.addActionListener(l);
    }

    public void addExtraListener(java.awt.event.ActionListener l) {
        extraButton.addActionListener(l);
    }
}
