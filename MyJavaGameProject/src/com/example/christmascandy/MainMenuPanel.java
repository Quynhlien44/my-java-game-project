package com.example.christmascandy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class MainMenuPanel extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L; // Add this line

    private JFrame frame;

    public MainMenuPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SoundManager.playSound("/sounds/christmasgame.wav");
                startGame();
            }
        });

        add(startButton, BorderLayout.CENTER);
    }

    private void startGame() {
    	
        frame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(frame);
        frame.getContentPane().add(gamePanel);
        frame.revalidate();
        frame.repaint();
        
        
        gamePanel.startGame();
    }
}