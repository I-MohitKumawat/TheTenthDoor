package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;

public class GamePanel extends Pane {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    private final Canvas canvas;
    private final GraphicsContext gc;

    // Simple test object
    private double playerX = 100;
    private double playerY = 100;
    final private double speed = 2;

    // event handlers
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        canvas = new Canvas(screenWidth, screenHeight);
        gc = canvas.getGraphicsContext2D();
        setPrefSize(screenWidth, screenHeight);
        getChildren().add(canvas);

        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) setupInput(newScene);
        });

        startGameLoop();
    }

    private void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
            }
        };
        gameLoop.start();
    }

    // updates player position
    private void update() {
        double dx = 0;
        double dy = 0;

        if (upPressed) dy -= 1;
        if (downPressed) dy += 1;
        if (leftPressed) dx -= 1;
        if (rightPressed) dx += 1;

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        playerX += dx * speed;
        playerY += dy * speed;

        // screen boundaries
        playerX = Math.max(0, Math.min(playerX, screenWidth - tileSize));
        playerY = Math.max(0, Math.min(playerY, screenHeight - tileSize));
    }

    private void draw() {
        // background fill
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, screenWidth, screenHeight);

        // Player
        gc.setFill(Color.RED);
        gc.fillRect(playerX, playerY, tileSize, tileSize);
    }

    private void setupInput(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) upPressed = true;
            if (e.getCode() == KeyCode.S) downPressed = true;
            if (e.getCode() == KeyCode.A) leftPressed = true;
            if (e.getCode() == KeyCode.D) rightPressed = true;
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) upPressed = false;
            if (e.getCode() == KeyCode.S) downPressed = false;
            if (e.getCode() == KeyCode.A) leftPressed = false;
            if (e.getCode() == KeyCode.D) rightPressed = false;
        });
    }
}
