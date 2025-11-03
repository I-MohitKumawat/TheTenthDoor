package main;

import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import tile.TileManager;

public class GamePanel extends Pane {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576

    private final Canvas canvas;
    private final GraphicsContext gc;
    private final GameInput input = new GameInput(); // event handeler
    public Player player = new Player(this, input);

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);

    public GamePanel() {
        canvas = new Canvas(screenWidth, screenHeight);
        gc = canvas.getGraphicsContext2D();
        setPrefSize(screenWidth, screenHeight);
        getChildren().add(canvas);

        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) input.attach(newScene);
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

        player.update();
    }

    private void draw() {

        // background fill
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, screenWidth, screenHeight);

        tileM.draw(gc);

        player.draw(gc);
    }
}
