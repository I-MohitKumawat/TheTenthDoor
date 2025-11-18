package main;

import object.*;

public class AssetSetter{
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0]= new OBJ_Door();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_FakeChest();
        gp.obj[1].worldX = 40 * gp.tileSize;
        gp.obj[1].worldY = 48 * gp.tileSize;

        gp.obj[2] = new OBJ_Boots();
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 42 * gp.tileSize;

        gp.obj[3] = new OBJ_RealChest();
        gp.obj[3].worldX = 44 * gp.tileSize;
        gp.obj[3].worldY = 9 * gp.tileSize;

    }
}
