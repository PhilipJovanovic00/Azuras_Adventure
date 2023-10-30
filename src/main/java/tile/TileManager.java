package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.io.InputStream;
import main.noise.FastNoiseLite;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    FastNoiseLite perlin = new FastNoiseLite();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }


    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*
    //If something breaks use this code to draw the map.
    //The Player will however not be in the middle of the map.

    public void draw(Graphics2D g2){

        for(int col = 0; col < gp.maxWorldCol; col++){
            for(int row = 0; row < gp.maxWorldRow; row++){

                int tileNum = mapTileNum[col][row];

                if(tileNum == 0){
                    g2.drawImage(tile[0].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNum == 1){
                    g2.drawImage(tile[1].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNum == 2){
                    g2.drawImage(tile[2].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNum == 3){
                    g2.drawImage(tile[3].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNum == 4){
                    g2.drawImage(tile[4].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNum == 5){
                    g2.drawImage(tile[5].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

     */

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}



