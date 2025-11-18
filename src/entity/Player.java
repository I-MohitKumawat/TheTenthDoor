package entity;

import javafx.scene.canvas.GraphicsContext;
import main.GameInput;
import main.GamePanel;

import javafx.scene.image.Image;

import java.awt.*;

public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    GamePanel gp;
    GameInput gi;
    public int hasKey = 0;
    int standCounter = 0;

    public Player(GamePanel gp, GameInput gi) {
        this.gp = gp;
        this.gi = gi;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 42;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = new Image(getClass().getResourceAsStream("/player/up1.png"));
        up2 = new Image(getClass().getResourceAsStream("/player/up2.png"));
        up3 = new Image(getClass().getResourceAsStream("/player/up3.png"));
        up4 = new Image(getClass().getResourceAsStream("/player/up4.png"));
        down1 = new Image(getClass().getResourceAsStream("/player/down1.png"));
        down2 = new Image(getClass().getResourceAsStream("/player/down2.png"));
        down3 = new Image(getClass().getResourceAsStream("/player/down3.png"));
        down4 = new Image(getClass().getResourceAsStream("/player/down4.png"));
        left1 = new Image(getClass().getResourceAsStream("/player/left1.png"));
        left2 = new Image(getClass().getResourceAsStream("/player/left2.png"));
        left3 = new Image(getClass().getResourceAsStream("/player/left3.png"));
        left4 = new Image(getClass().getResourceAsStream("/player/left4.png"));
        right1 = new Image(getClass().getResourceAsStream("/player/right1.png"));
        right2 = new Image(getClass().getResourceAsStream("/player/right2.png"));
        right3 = new Image(getClass().getResourceAsStream("/player/right3.png"));
        right4 = new Image(getClass().getResourceAsStream("/player/right4.png"));

    }

    public void update() {

        if (gi.isUp() || gi.isDown() || gi.isLeft() || gi.isRight()) {
            if (gi.isUp()) {
                direction = "up";
            }
            if (gi.isDown()) {
                direction = "down";
            }
            if (gi.isLeft()) {
                direction = "left";
            }
            if (gi.isRight()) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //  CHECK OBJECT COLLISION
            int objIndex =gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
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

            // animation
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2: (spriteNum ==2) ? 3 : (spriteNum ==3)?4:1;
                spriteCounter = 0;
            }
        }else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;

            }
        }

        // screen boundaries
//        worldX = Math.max(0, Math.min(worldX, gp.screenWidth - gp.tileSize));
//        worldY = Math.max(0, Math.min(worldY, gp.screenHeight - gp.tileSize));
    }
    public void pickUpObject(int i){

        if(i != 999){
            String objectName = gp.obj[i].name;
            switch (objectName){
                case"RealChest":
                    gp.playSoundEffect(3);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You Picked a KEY");
                    break;

                case"FakeChest":
                    gp.playSoundEffect(2);
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got Fooled by FakeChest");
                    break;

                case"Door":
                    gp.playSoundEffect(1);
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door Unlocked");
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
//                        gp.playSoundEffect(6);
                    }else{
                        gp.ui.showMessage("You need a key");
                    }
                    break;
                case"Boots":
                    gp.playSoundEffect(2);
                    speed +=1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speeeeeeed!!!");
                    break;
            }
        }
    }

    public void draw(GraphicsContext gc) {

        // gc.setFill(Color.RED);
        // gc.fillRect(x, y, gp.tileSize, gp.tileSize);

        Image image = switch (direction) {
            case "up" -> (spriteNum == 1) ? up1 :
                    (spriteNum == 2) ? up2 : (spriteNum == 3) ? up3 : up4;
            case "down" -> (spriteNum == 1) ? down1 :
                    (spriteNum == 2) ? down2 : (spriteNum == 3) ? down3 : down4;
            case "left" -> (spriteNum == 1) ? left1 :
                    (spriteNum == 2) ? left2 : (spriteNum == 3) ? left3 : left4;
            case "right" -> (spriteNum == 1) ? right1 :
                    (spriteNum == 2) ? right2 : (spriteNum == 3) ? right3 : right4;
            default -> up1;
        };

        gc.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize);

    }
}
