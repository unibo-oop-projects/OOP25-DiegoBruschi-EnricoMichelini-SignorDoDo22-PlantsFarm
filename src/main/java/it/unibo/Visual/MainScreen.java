package it.unibo.Visual;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;    
import java.awt.Toolkit;

public class MainScreen {

    //Constants for proprtions
    private static final double GENERAL_GAP_RATIO = 0.01;     
    private static final double GAME_GAP_RATIO = 0.10;          
    private static final double MENU_WIDTH_RATIO = 0.125;      
    private static final double BUTTON_SIZE_RATIO = 0.075;      

    private JFrame frame;

    // Components
    private JButton shopButton;
    private JButton encyclopediaButton;
    private JButton inventoryButton;
    private JButton settingsButton;

    private JPanel gamePanel;
    private JPanel menuPanel;
    
    // NUOVO COMPONENTE
    private JLabel coinLabel;

    public MainScreen() {
        createMainScreen();
    }

    private void createMainScreen() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        final int width = (int) screenSize.getWidth();
        final int height = (int) screenSize.getHeight();

        frame = new JFrame("Main Screen");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
        
        final int borderGap = (int) (width * GENERAL_GAP_RATIO); 
        frame.setLayout(new BorderLayout(borderGap, borderGap));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(222, 184, 135));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));

        coinLabel = new JLabel("Coins: 0"); 
        coinLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); 
        coinLabel.setForeground(new Color(139, 69, 19));
        
        topPanel.add(coinLabel);
        frame.add(topPanel, BorderLayout.NORTH);
 

        // Game Screen
        JPanel window = new JPanel();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setVisible(true);

        gamePanel.startGameThread();

        // Menu Screen
        menuPanel = new JPanel();
        
        final int menuGap = (int) (height * GENERAL_GAP_RATIO);
        menuPanel.setLayout(new GridLayout(4, 1, menuGap, menuGap));
        
        final int menuWidth = (int) (width * MENU_WIDTH_RATIO); 
        menuPanel.setPreferredSize(new Dimension(menuWidth, 0));
        menuPanel.setBackground(new Color(222, 184, 135));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        // Buttons
        shopButton = createButton("SHOP", Texture.SHOP_ICON);
        encyclopediaButton = createButton("ENCYCLOPEDIA", Texture.ENCYCLOPEDIA_ICON);
        inventoryButton = createButton("INVENTORY", Texture.INVENTORY_ICON);
        settingsButton = createButton("SETTINGS", Texture.SETTINGS_ICON);

        menuPanel.add(shopButton);
        menuPanel.add(encyclopediaButton);
        menuPanel.add(inventoryButton);
        menuPanel.add(settingsButton);

        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.EAST);
    }


    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    private JButton createButton(String text, Icon icon) {
        JButton button = new JButton(text, icon);
        Color BROWN = new Color(139, 100, 25);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int btnSize = (int) (screenSize.getWidth() * BUTTON_SIZE_RATIO); 

        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setPreferredSize(new Dimension(btnSize, btnSize));
        button.setFocusPainted(false);
        button.setBackground(BROWN);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        return button;
    }

    public void addShopListener(java.awt.event.ActionListener l) {
        shopButton.addActionListener(l);
    }

    public void addEncyclopediaListener(java.awt.event.ActionListener l) {
        encyclopediaButton.addActionListener(l);
    }

    public void addInventoryListener(java.awt.event.ActionListener l) {
        inventoryButton.addActionListener(l);
    }

    public void addSettingsListener(java.awt.event.ActionListener l) {
        settingsButton.addActionListener(l);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void updateCoinLabel(int coins) {
        coinLabel.setText("Coins: " + coins);
    }
}







