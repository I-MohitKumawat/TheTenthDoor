package object;

import javafx.scene.image.Image;

public class OBJ_Chest extends SuperObject{


    public OBJ_Chest(){

        name = "chest";
        image = new Image(getClass().getResourceAsStream("/objects/chest.png"));
    }
}
