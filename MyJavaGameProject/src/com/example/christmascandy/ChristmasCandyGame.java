package com.example.christmascandy;

import javax.swing.*;

public class ChristmasCandyGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Christmas Candy Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.setLocationRelativeTo(null);

            MainMenuPanel menuPanel = new MainMenuPanel(frame);
            frame.getContentPane().add(menuPanel);

            frame.setVisible(true);
        });
    }
}