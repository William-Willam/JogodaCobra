package org.example.snakegame;

import java.util.Random;

//Essa classe cuida apenas da comida:
//guardar a posição e se reposicionar aleatoriamente sem cair na cobra.

public class Food {

    private int col;
    private int row;
    private final Random random = new Random();

    public Food(Snake snake, int cols, int rows) {
        respawn(snake, cols, rows);
    }

    // Gera nova posição aleatória fora do corpo da cobra
    public void respawn(Snake snake, int cols, int rows) {
        int newCol, newRow;
        do {
            newCol = random.nextInt(cols);
            newRow = random.nextInt(rows);
        } while (snake.occupies(newCol, newRow));

        col = newCol;
        row = newRow;
    }

    public int getCol() { return col; }
    public int getRow() { return row; }
}