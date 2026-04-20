package org.example.snakegame;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    // Impede a cobra de virar 180° (voltar sobre si mesma)
    public boolean isOpposite(Direction other) {
        return (this == UP    && other == DOWN)
                || (this == DOWN  && other == UP)
                || (this == LEFT  && other == RIGHT)
                || (this == RIGHT && other == LEFT);
    }
}