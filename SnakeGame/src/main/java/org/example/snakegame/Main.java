package org.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(
                GameLoop.COLS * GameLoop.CELL_SIZE,
                GameLoop.ROWS * GameLoop.CELL_SIZE
        );

        GameLoop gameLoop = new GameLoop(canvas.getGraphicsContext2D());

        Scene scene = new Scene(new StackPane(canvas),
                GameLoop.COLS * GameLoop.CELL_SIZE,
                GameLoop.ROWS * GameLoop.CELL_SIZE
        );

        scene.setOnKeyPressed(gameLoop::handleKey);

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}