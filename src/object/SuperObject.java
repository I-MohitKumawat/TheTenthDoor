package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GamePanel;

import java.awt.*;

public class SuperObject {

    public Image image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public void draw(GraphicsContext gc, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (
                worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                        worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                        worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                        worldY - gp.tileSize< gp.player.worldY + gp.player.screenY
        ) {
            gc.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize);
        }
    }
}
