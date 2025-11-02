package entity;

import javafx.scene.canvas.GraphicsContext;
import main.GameInput;
import main.GamePanel;

import javafx.scene.image.Image;

import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    GameInput gi;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, GameInput gi) {
        this.gp = gp;
        this.gi = gi;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize*25;
        worldY = gp.tileSize*25;
        speed = 4.0;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = new Image(getClass().getResourceAsStream("/player/up.png"));
        up2 = new Image(getClass().getResourceAsStream("/player/up1.png"));
        up3 = new Image(getClass().getResourceAsStream("/player/up3.png"));
        down1 = new Image(getClass().getResourceAsStream("/player/down.png"));
        down2 = new Image(getClass().getResourceAsStream("/player/down1.png"));
        down3 = new Image(getClass().getResourceAsStream("/player/down3.png"));
        left1 = new Image(getClass().getResourceAsStream("/player/left.png"));
        left2 = new Image(getClass().getResourceAsStream("/player/left1.png"));
        left3 = new Image(getClass().getResourceAsStream("/player/left3.png"));
        right1 = new Image(getClass().getResourceAsStream("/player/right.png"));
        right2 = new Image(getClass().getResourceAsStream("/player/right1.png"));
        right3 = new Image(getClass().getResourceAsStream("/player/right3.png"));

    }

    public void update() {
        double dx = 0, dy = 0;

        if (gi.isUp())    { direction = "up";    dy -= 1; }
        if (gi.isDown())  { direction = "down";  dy += 1; }
        if (gi.isLeft())  { direction = "left";  dx -= 1; }
        if (gi.isRight()) { direction = "right"; dx += 1; }

        // animation
        if (dx != 0 || dy != 0) {
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= (length * 0.6); //0.6 componsation for slower perception
            dy /= (length * 0.6);
        }

        worldX += dx * speed;
        worldY += dy * speed;

        // screen boundaries
//        worldX = Math.max(0, Math.min(worldX, gp.screenWidth - gp.tileSize));
//        worldY = Math.max(0, Math.min(worldY, gp.screenHeight - gp.tileSize));
    }

    public void draw(GraphicsContext gc) {

        // gc.setFill(Color.RED);
        // gc.fillRect(x, y, gp.tileSize, gp.tileSize);

        Image image = switch (direction) {
            case "up" -> (spriteNum == 1) ? up1 :
                    (spriteNum == 2) ? up2 : up3;
            case "down" -> (spriteNum == 1) ? down1 :
                    (spriteNum == 2) ? down2 : down3;
            case "left" -> (spriteNum == 1) ? left1 :
                    (spriteNum == 2) ? left2 : left3;
            case "right" -> (spriteNum == 1) ? right1 :
                    (spriteNum == 2) ? right2 : right3;
            default -> up1;
        };

        gc.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize);

    }
}
