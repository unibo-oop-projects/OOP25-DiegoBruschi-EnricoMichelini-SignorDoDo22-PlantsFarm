package it.unibo.Visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class EncyclopediaFrame {

    //Constants for proprtions
    private static final double FRAME_WIDTH_RATIO = 0.70;           
    private static final double FRAME_HEIGHT_RATIO = 0.75;         

    private static final double RIGHT_PANEL_WIDTH_RATIO = 0.45;     
    private static final double TOP_PANEL_HEIGHT_RATIO = 0.60;      
    private static final double GAP_RATIO = 0.01;                 

    private final JFrame mainFrame;
   
    //Components
    private JFrame encyclopediaMainFrame;
    private JPanel leftPlantPanel;
    private JLabel rightPlantImageLabel;
    private JLabel rightPlantDescriptionLabel;
    private JButton stageButton;

    public EncyclopediaFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        createUserInterface();
    }

    private void createUserInterface() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenW = (int) screenSize.getWidth();
        final int screenH = (int) screenSize.getHeight();

        final int frameWidth = (int) (screenW * FRAME_WIDTH_RATIO);
        final int frameHeight = (int) (screenH * FRAME_HEIGHT_RATIO);

        encyclopediaMainFrame = new JFrame("Encyclopedia");
        encyclopediaMainFrame.setSize(frameWidth, frameHeight);
        encyclopediaMainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        encyclopediaMainFrame.setResizable(false);
        encyclopediaMainFrame.setLocationRelativeTo(mainFrame);
        encyclopediaMainFrame.setLayout(new BorderLayout());

        final int gap = (int) (frameWidth * GAP_RATIO);

        leftPlantPanel = new JPanel(new GridLayout(6, 4, gap, gap));
        leftPlantPanel.setBorder(BorderFactory.createTitledBorder("Plants"));

        JPanel rightPanel = new JPanel(new BorderLayout());
        
        final int rightPanelWidth = (int) (frameWidth * RIGHT_PANEL_WIDTH_RATIO);
        rightPanel.setPreferredSize(new Dimension(rightPanelWidth, 0));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Plant Info"));

        JPanel topPanel = new JPanel(new BorderLayout());
        
        final int topPanelHeight = (int) (frameHeight * TOP_PANEL_HEIGHT_RATIO);
        topPanel.setPreferredSize(new Dimension(rightPanelWidth, topPanelHeight));
        topPanel.setBorder(BorderFactory.createEmptyBorder(gap/2, gap/2, gap/2, gap/2));

        stageButton = new JButton("Stage 1");
        stageButton.setVisible(false);
        rightPlantImageLabel = new JLabel("", SwingConstants.CENTER);

        topPanel.add(stageButton, BorderLayout.NORTH);
        topPanel.add(rightPlantImageLabel, BorderLayout.CENTER);

        rightPlantDescriptionLabel = new JLabel();
        rightPlantDescriptionLabel.setVerticalAlignment(SwingConstants.TOP);
        rightPlantDescriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);

        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(rightPlantDescriptionLabel, BorderLayout.CENTER);

        encyclopediaMainFrame.add(leftPlantPanel, BorderLayout.CENTER);
        encyclopediaMainFrame.add(rightPanel, BorderLayout.EAST);
    }


    //Application

    public void showEncyclopediaScreen() {
        encyclopediaMainFrame.setVisible(true);
    }
    public void showStageButton(){
        stageButton.setVisible(true);
    }

    public JButton addPlantButton(String displayName, Icon icon) {
        JButton newButton = new JButton(displayName, icon);
        newButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        leftPlantPanel.add(newButton);
        return newButton;
    }

    public void setPlantImage(Icon icon) {
        rightPlantImageLabel.setIcon(icon);
    }

    public void setPlantDescription(String plantInfo) {
        rightPlantDescriptionLabel.setText(plantInfo);
    }

    public void setStageButtonText(String stage) {
        stageButton.setText(stage);
    }

    public void addStageButtonListener(java.awt.event.ActionListener l) {
        stageButton.addActionListener(l);
    }
}






