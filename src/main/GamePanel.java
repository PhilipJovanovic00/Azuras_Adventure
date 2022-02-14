package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16;  // 16 x 16 Tiles
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48 x 48 Tiles
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 Pixels

    Thread gameThread;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#000000"));
        this.setDoubleBuffered(true);


    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        while (gameThread != null) {
//            System.out.println("The Loop is running");

            // 1 Update: update information such as character positions
            update();

            // 2 Draw: Draw the screen with the updated information
            repaint();

        }
    }
        public void update() {

        }
        public void paintComponent(Graphics g){

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.decode("#ffffff"));

            g2.fillRect(100, 100, tileSize, tileSize);

            g2.dispose();


        }


    }

