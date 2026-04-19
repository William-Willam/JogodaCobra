# 🐍 Snake Game

Um clássico jogo da cobrinha feito em Java com JavaFX.

## 🎮 Como Jogar

- Use as **setas do teclado** ou **WASD** para mover a cobra
- Coma a comida vermelha para crescer e ganhar pontos
- Evite bater nas bordas ou no próprio corpo
- A cobra acelera a cada 5 pontos
- Pressione qualquer tecla para reiniciar após o Game Over

## 🏆 Pontuação

| Ação         | Pontos |
|--------------|--------|
| Comer comida | +1     |

## 🚀 Como Executar

### Pré-requisitos
- Java 21+
- Maven

### Pelo IntelliJ
1. Abra o projeto
2. Execute `Main.java`

### Pelo terminal

```bash
# Compilar e gerar o JAR
mvn package

# Rodar o JAR
java -jar target/SnakeGame-1.0-SNAPSHOT.jar
```

## 🗂️ Estrutura do Projeto

```
src/main/java/org/example/snakegame/
├── Main.java         → inicializa a janela JavaFX
├── GameLoop.java     → lógica principal e estados do jogo
├── Snake.java        → movimentação e colisões da cobra
├── Food.java         → posicionamento da comida
├── Renderer.java     → desenho do canvas
└── Direction.java    → enum de direções
```

## 🛠️ Tecnologias

- Java 22
- JavaFX 21
- Maven

## 📚 Aprendizados

- Estrutura de projeto JavaFX com Maven
- Game loop com `AnimationTimer`
- Desenho com `Canvas` e `GraphicsContext`
- Separação de responsabilidades (SRP)
- Detecção de colisões
