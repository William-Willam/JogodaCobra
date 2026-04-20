package org.example.snakegame;
//Essa classe é o cérebro do jogo.
// Coordena tudo: estados, input, velocidade e manda cada classe fazer seu trabalho.

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class GameLoop {

    static final int  COLS      = 20;
    static final int  ROWS      = 20;
    static final int  CELL_SIZE = 20;
    static final long SPEED_MAX = 150_000_000L; // velocidade inicial
    static final long SPEED_MIN = 80_000_000L;  // velocidade máxima

    private final Renderer renderer;

    private Snake snake;
    private Food  food;

    private int  score     = 0;
    private int  highScore = 0;
    private long currentSpeed = SPEED_MAX;
    private long lastUpdate   = 0;

    enum GameState { START, PLAYING, GAME_OVER }
    private GameState state = GameState.START;

    private final AnimationTimer timer;

    public GameLoop(GraphicsContext gc) {
        renderer = new Renderer(gc, COLS, ROWS, CELL_SIZE);
        snake    = new Snake();
        food     = new Food(snake, COLS, ROWS);

        // Define o loop principal
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (state != GameState.PLAYING) return;
                if (now - lastUpdate >= currentSpeed) {
                    lastUpdate = now;
                    update();
                }
            }
        };
    }

    public void start() {
        renderer.drawStart();
        timer.start();
    }

    // ─── Input ──────────────────────────────────────────────

    public void handleKey(KeyEvent e) {
        switch (state) {
            case START, GAME_OVER -> resetGame();
            case PLAYING -> {
                Direction d = switch (e.getCode()) {
                    case UP,    W -> Direction.UP;
                    case DOWN,  S -> Direction.DOWN;
                    case LEFT,  A -> Direction.LEFT;
                    case RIGHT, D -> Direction.RIGHT;
                    default -> null;
                };
                if (d != null) snake.setDirection(d);
            }
        }
    }

    // ─── Lógica ─────────────────────────────────────────────

    private void update() {
        snake.move();

        if (snake.isOutOfBounds(COLS, ROWS) || snake.collidesWithSelf()) {
            endGame();
            return;
        }

        if (snake.headAt(food.getCol(), food.getRow())) {
            score++;
            snake.grow();
            food.respawn(snake, COLS, ROWS);
            updateSpeed();
        }

        renderer.drawGame(snake, food, score, highScore);
    }

    private void updateSpeed() {
        long reduction = (score / 5) * 10_000_000L;
        currentSpeed = Math.max(SPEED_MIN, SPEED_MAX - reduction);
    }

    private void endGame() {
        if (score > highScore) highScore = score;
        state = GameState.GAME_OVER;
        renderer.drawGame(snake, food, score, highScore);
        renderer.drawGameOver(score, highScore);
    }

    private void resetGame() {
        snake.reset();
        food.respawn(snake, COLS, ROWS);
        score        = 0;
        currentSpeed = SPEED_MAX;
        state        = GameState.PLAYING;
    }
}