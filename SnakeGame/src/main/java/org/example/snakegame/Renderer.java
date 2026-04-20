package org.example.snakegame;

//Essa classe cuida apenas de desenhar.
//Recebe os dados e pinta tudo no canvas — não tem nenhuma lógica de jogo.

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Renderer {

    private final GraphicsContext gc;
    private final int cols;
    private final int rows;
    private final int cellSize;

    public Renderer(GraphicsContext gc, int cols, int rows, int cellSize) {
        this.gc       = gc;
        this.cols     = cols;
        this.rows     = rows;
        this.cellSize = cellSize;
    }

    public void drawGame(Snake snake, Food food, int score, int highScore) {
        drawBackground();
        drawGrid();
        drawFood(food);
        drawSnake(snake);
        drawHUD(score, highScore);
    }

    public void drawStart() {
        int w = cols * cellSize;
        int h = rows * cellSize;

        drawBackground();

        gc.setFill(Color.LIMEGREEN);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("SNAKE", w / 2.0, h / 2.0 - 30);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 16));
        gc.fillText("Pressione qualquer tecla", w / 2.0, h / 2.0 + 10);

        gc.setFill(Color.GRAY);
        gc.setFont(Font.font("Arial", 13));
        gc.fillText("Use as setas ou WASD", w / 2.0, h / 2.0 + 35);
    }

    public void drawGameOver(int score, int highScore) {
        int w = cols * cellSize;
        int h = rows * cellSize;

        // Overlay escuro
        gc.setFill(Color.rgb(0, 0, 0, 0.65));
        gc.fillRect(0, 0, w, h);

        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("GAME OVER", w / 2.0, h / 2.0 - 20);

        gc.setFill(Color.YELLOW);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gc.fillText("Score: " + score, w / 2.0, h / 2.0 + 10);

        if (score == highScore && score > 0) {
            gc.setFill(Color.ORANGE);
            gc.setFont(Font.font("Arial", 13));
            gc.fillText("🏆 Novo recorde!", w / 2.0, h / 2.0 + 30);
        }

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 14));
        gc.fillText("Pressione qualquer tecla", w / 2.0, h / 2.0 + 52);
    }

    // ─── Métodos privados de desenho ────────────────────────

    private void drawBackground() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, cols * cellSize, rows * cellSize);
    }

    private void drawGrid() {
        gc.setStroke(Color.rgb(30, 30, 30));
        gc.setLineWidth(0.5);
        for (int i = 0; i <= cols; i++)
            gc.strokeLine(i * cellSize, 0, i * cellSize, rows * cellSize);
        for (int j = 0; j <= rows; j++)
            gc.strokeLine(0, j * cellSize, cols * cellSize, j * cellSize);
    }

    private void drawFood(Food food) {
        gc.setFill(Color.RED);
        gc.fillOval(
                food.getCol() * cellSize + 2,
                food.getRow() * cellSize + 2,
                cellSize - 4,
                cellSize - 4
        );
    }

    private void drawSnake(Snake snake) {
        var body = snake.getBody();
        for (int i = 0; i < body.size(); i++) {
            int x = body.get(i)[0] * cellSize;
            int y = body.get(i)[1] * cellSize;
            gc.setFill(i == 0 ? Color.LIMEGREEN : Color.GREEN);
            gc.fillRoundRect(x + 1, y + 1, cellSize - 2, cellSize - 2, 4, 4);
        }
    }

    private void drawHUD(int score, int highScore) {
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Score: " + score, 8, 18);

        gc.setFill(Color.YELLOW);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText("Best: " + highScore, cols * cellSize - 8, 18);
    }
}