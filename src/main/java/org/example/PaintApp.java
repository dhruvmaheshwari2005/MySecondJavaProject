package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PaintApp extends JFrame {

    private final CanvasPanel canvas;
    private Color currentColor = Color.BLACK;

    public PaintApp() {
        setTitle("Paint Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and add the canvas panel
        canvas = new CanvasPanel();
        add(canvas, BorderLayout.CENTER);

        // Create and add the control panel
        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        // Add color buttons
        String[] colors = {"Black", "Red", "Green", "Blue", "Yellow", "Orange"};
        for (String colorName : colors) {
            JButton colorButton = new JButton(colorName);
            colorButton.addActionListener(new ColorButtonListener());
            controlPanel.add(colorButton);
        }

        // Add clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> canvas.clear());
        controlPanel.add(clearButton);
    }

    private class ColorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            switch (source.getText()) {
                case "Black" -> currentColor = Color.BLACK;
                case "Red" -> currentColor = Color.RED;
                case "Green" -> currentColor = Color.GREEN;
                case "Blue" -> currentColor = Color.BLUE;
                case "Yellow" -> currentColor = Color.YELLOW;
                case "Orange" -> currentColor = Color.ORANGE;
            }
            canvas.setCurrentColor(currentColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaintApp app = new PaintApp();
            app.setVisible(true);
        });
    }
}

class CanvasPanel extends JPanel {

    private Image image;
    private Graphics2D g2d;
    private int prevX, prevY;
    private Color currentColor = Color.BLACK;

    public CanvasPanel() {
        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (g2d != null) {
                    g2d.setColor(currentColor);
                    g2d.drawLine(prevX, prevY, x, y);
                    repaint();
                    prevX = x;
                    prevY = y;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2d = (Graphics2D) image.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        if (g2d != null) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(0, 0, getSize().width, getSize().height);
            g2d.setPaint(Color.BLACK);
            repaint();
        }
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
}
