package org.example.snakegame;

// Representa a cobra em si.
// Sabe se mover, crescer, mudar de direção e
// detectar se colidiu com a borda ou com o próprio corpo.
// Não sabe nada de desenho ou de janela.

import java.util.LinkedList;

public class Snake {

    private final LinkedList<int[]> body = new LinkedList<>();
    private Direction direction     = Direction.RIGHT;
    private Direction nextDirection = Direction.RIGHT;
    private boolean grew = false;

    public Snake() {
        body.add(new int[]{10, 10}); // cabeça
        body.add(new int[]{9,  10});
        body.add(new int[]{8,  10}); // rabo
    }

    // Aplica a direção do buffer e move a cobra
    public void move() {
        direction = nextDirection;

        int[] head = body.getFirst();
        int newCol = head[0] + switch (direction) {
            case LEFT  -> -1;
            case RIGHT ->  1;
            default    ->  0;
        };
        int newRow = head[1] + switch (direction) {
            case UP   -> -1;
            case DOWN ->  1;
            default   ->  0;
        };

        body.addFirst(new int[]{newCol, newRow});

        if (grew) {
            grew = false; // mantém o rabo (cresceu)
        } else {
            body.removeLast();
        }
    }

    public void grow() {
        grew = true;
    }

    public void setDirection(Direction d) {
        if (!direction.isOpposite(d)) {
            nextDirection = d;
        }
    }

    // Verifica se a cabeça saiu dos limites
    public boolean isOutOfBounds(int cols, int rows) {
        int[] head = body.getFirst();
        return head[0] < 0 || head[0] >= cols
                || head[1] < 0 || head[1] >= rows;
    }

    // Verifica se a cabeça colidiu com o próprio corpo
    public boolean collidesWithSelf() {
        int[] head = body.getFirst();
        return body.stream().skip(1)
                .anyMatch(s -> s[0] == head[0] && s[1] == head[1]);
    }

    // Verifica se uma célula está ocupada pela cobra
    public boolean occupies(int col, int row) {
        return body.stream().anyMatch(s -> s[0] == col && s[1] == row);
    }

    // Verifica se a cabeça está em determinada posição
    public boolean headAt(int col, int row) {
        int[] head = body.getFirst();
        return head[0] == col && head[1] == row;
    }

    public void reset() {
        body.clear();
        body.add(new int[]{10, 10});
        body.add(new int[]{9,  10});
        body.add(new int[]{8,  10});
        direction     = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
        grew          = false;
    }

    public LinkedList<int[]> getBody() {
        return body;
    }
}