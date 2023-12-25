package com.example.christmascandy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Dimension;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private int candyFallingSpeed;
    private int playerX, playerY;
    private int candyX, candyY;
    private int score;
    private Image backgroundImage;
    private ImageIcon playerIcon, candyIcon;
    private JFrame frame;
  

    public GamePanel(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(1280, 720));
        setBackground(Color.CYAN);

        initializeGame();
        candyFallingSpeed = 15;

        timer = new Timer(20, this);
        playerIcon = new ImageIcon(getClass().getResource("/images/player.png"));
        System.out.println(getClass().getResource("/images/player.png"));
        candyIcon = new ImageIcon(getClass().getResource("/images/candy.png"));

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        addMouseMotionListener(this);

        loadImage("/images/background.jpg");
    }

    private void initializeGame() {
        playerX = 150;
        playerY = 400;
        candyX = generateRandomX();
        candyY = 0;
        score = 0;
    }

    public void startGame() {
        initializeGame();
        timer.start();
        requestFocusInWindow();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        moveCandy();
        checkCollision();
        repaint();
        if (playerY > getHeight()) {
            gameOver();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && playerX > 0) {
            playerX -= 10;
        } else if (key == KeyEvent.VK_RIGHT && playerX < 350) {
            playerX += 10;
        }
        candyFallingSpeed = 15 + Math.abs(playerX - 150) / 10;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private int generateRandomX() {
        Random random = new Random();
        return random.nextInt(900);
    }
    
    private void loadImage(String path) {
        backgroundImage = new ImageIcon(getClass().getResource(path)).getImage();
    }
    
    private void moveCandy() {
        candyY += candyFallingSpeed;
        if (candyY > 510) {
            candyY = 0;
            candyX = generateRandomX();
        }
    }

    private void checkCollision() {
        if (playerX < candyX && candyX < playerX + 50 && playerY < candyY && candyY < playerY + 50) {
            score++;
            candyY = 0;
            candyX = generateRandomX();
        }
    }

    private void gameOver() {
        timer.stop();
        SoundManager.playSound("/sounds/game-over.wav");
        int option = JOptionPane.showConfirmDialog(this, "Game Over! Your Score: " + score +
                "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }
    
    private void resetGame() {
        frame.getContentPane().removeAll();
        MainMenuPanel menuPanel = new MainMenuPanel(frame);
        frame.getContentPane().add(menuPanel);
        frame.revalidate();
        frame.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawPlayer(g);
        drawCandy(g);
        drawScore(g);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        playerX = mouseX;
        checkCollision();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, this);
    }

    private void drawPlayer(Graphics g) {
        playerIcon.paintIcon(this, g, playerX, playerY);
    }

    private void drawCandy(Graphics g) {
        candyIcon.paintIcon(this, g, candyX, candyY);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
    }
}