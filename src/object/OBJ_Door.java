package object;

import javafx.scene.image.Image;

public class OBJ_Door extends SuperObject {


    public OBJ_Door() {

        name = "Door";
        image = new Image(getClass().getResourceAsStream("/objects/door.png"));
        collision = true;
    }
}
