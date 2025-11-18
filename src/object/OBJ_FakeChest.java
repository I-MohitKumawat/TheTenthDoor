package object;

import javafx.scene.image.Image;

public class OBJ_FakeChest extends SuperObject {

    public OBJ_FakeChest(){

        name = "FakeChest";
        image = new Image(getClass().getResourceAsStream("/objects/rfcc.png"));
    }
}
