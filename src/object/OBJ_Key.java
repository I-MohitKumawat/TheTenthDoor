package object;

import javafx.scene.image.Image;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(){

        name = "key";
        image = new Image(getClass().getResourceAsStream("/objects/key.png"));
    }
}
