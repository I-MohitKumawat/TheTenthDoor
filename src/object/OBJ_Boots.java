package object;

import javafx.scene.image.Image;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots(){

        name = "boots";
        image = new Image(getClass().getResourceAsStream("/objects/boots.png"));
    }
}
