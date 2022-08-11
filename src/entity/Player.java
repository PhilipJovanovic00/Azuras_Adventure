package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {


        //The players default position
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

    }

    //Gets all resources for the player
    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Azura_Right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    //Update Method for KeyInput of the User, the Collision is also checked in here.
    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {


            if (keyH.upPressed == true) {
                direction = "up";
                //worldY -= speed;
            }
            if (keyH.downPressed == true) {
                direction = "down";
                //worldY += speed;
            }
            if (keyH.leftPressed == true) {
                direction = "left";
                //worldX -= speed;
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                //worldX += speed;
            }

            //Check if there is a collision with the solid area (tile)
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }


            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }


    }

    public void pickUpObject(int i) {

        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key: " + hasKey);
                    break;
                case "Boots":
                    speed += 2;
                    gp.obj[i] = null;
                    break;
            }

        }
    }

    //Draws the player on the screen
    //Changes the image depending on the direction and the images for the same direction
    //The image is also changed depending on the spriteCounter
    //This is for a better animation, so the player doesn't look like he is floating over the map
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
