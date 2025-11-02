package main;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class GameInput {
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public void attach(Scene scene) {
        scene.setOnKeyPressed(e -> setKey(e.getCode(), true));
        scene.setOnKeyReleased(e -> setKey(e.getCode(), false));
    }

    private void setKey(KeyCode code, boolean pressed) {
        switch (code) {
            case W -> upPressed = pressed;
            case S -> downPressed = pressed;
            case A -> leftPressed = pressed;
            case D -> rightPressed = pressed;
        }
    }

    public boolean isUp() { return upPressed; }
    public boolean isDown() { return downPressed; }
    public boolean isLeft() { return leftPressed; }
    public boolean isRight() { return rightPressed; }
}

