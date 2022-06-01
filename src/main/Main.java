package main;

import javax.swing.*;
import java.awt.*;

//Main class for the game, just creates the main frame and starts the game
public class Main {



    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Azuras Adventure");

        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/icon/favicon-32x32.png")));

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
