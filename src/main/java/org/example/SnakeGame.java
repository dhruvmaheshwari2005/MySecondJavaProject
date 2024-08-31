package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    static final int TILE_SIZE = 25;
    static final int BOARD_SIZE = 20;
    static final int INITIAL_SNAKE_LENGTH = 5;
    static final int DELAY = 100;

    private SnakeBoard board;
    private boolean running = false;

    public SnakeGame() {
        board = new SnakeBoard();
        add(board);

        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE);
        setLocationRelativeTo(null);
        setResizable(false);

        initGame();
    }

    private void initGame() {
        running = true;
        board.startGame();
        Timer timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    board.move();
                    board.repaint();
                }
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                board.changeDirection(e.getKeyCode());
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}

class SnakeBoard extends JPanel {

    private LinkedList<Point> snake;
    private Point food;
    private int direction;
    private boolean grow = false;

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private static final Color SNAKE_COLOR = Color.GREEN;
    private static final Color FOOD_COLOR = Color.RED;

    public SnakeBoard() {
        snake = new LinkedList<>();
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void startGame() {
        direction = RIGHT;
        snake.clear();

        for (int i = 0; i < SnakeGame.INITIAL_SNAKE_LENGTH; i++) {
            snake.add(new Point(SnakeGame.BOARD_SIZE / 2 - i, SnakeGame.BOARD_SIZE / 2));
        }

        placeFood();
    }

    public void move() {
        Point head = new Point(snake.getFirst());

        switch (direction) {
            case UP -> head.y--;
            case RIGHT -> head.x++;
            case DOWN -> head.y++;
            case LEFT -> head.x--;
        }

        if (head.equals(food)) {
            grow = true;
            placeFood();
        }

        if (head.x < 0 || head.x >= SnakeGame.BOARD_SIZE || head.y < 0 || head.y >= SnakeGame.BOARD_SIZE || snake.contains(head)) {
            // Game over condition
            JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.ERROR_MESSAGE);
            startGame();
            return;
        }

        snake.addFirst(head);

        if (!grow) {
            snake.removeLast();
        } else {
            grow = false;
        }
    }

    public void changeDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP -> {
                if (direction != DOWN) direction = UP;
            }
            case KeyEvent.VK_RIGHT -> {
                if (direction != LEFT) direction = RIGHT;
            }
            case KeyEvent.VK_DOWN -> {
                if (direction != UP) direction = DOWN;
            }
            case KeyEvent.VK_LEFT -> {
                if (direction != RIGHT) direction = LEFT;
            }
        }
    }

    private void placeFood() {
        Random random = new Random();
        do {
            food = new Point(random.nextInt(SnakeGame.BOARD_SIZE), random.nextInt(SnakeGame.BOARD_SIZE));
        } while (snake.contains(food));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw snake
        g.setColor(SNAKE_COLOR);
        for (Point point : snake) {
            g.fillRect(point.x * SnakeGame.TILE_SIZE, point.y * SnakeGame.TILE_SIZE, SnakeGame.TILE_SIZE, SnakeGame.TILE_SIZE);
        }

        // Draw food
        g.setColor(FOOD_COLOR);
        g.fillRect(food.x * SnakeGame.TILE_SIZE, food.y * SnakeGame.TILE_SIZE, SnakeGame.TILE_SIZE, SnakeGame.TILE_SIZE);
    }
}
