package object;

import javafx.scene.image.Image;

public class OBJ_RealChest extends SuperObject{

    public OBJ_RealChest(){

        name = "RealChest";
        image = new Image(getClass().getResourceAsStream("/objects/rfcc.png"));
    }
}
