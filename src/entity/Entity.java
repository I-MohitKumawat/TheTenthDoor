package entity;

import javafx.scene.image.Image;

public class Entity {
    public double x, y;
    public double speed;

    public Image up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
