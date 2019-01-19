package graphics;

import javax.swing.*;
import java.awt.*;


public class Square extends JPanel {

    protected int x;
    protected int y;
    protected int side1;
    protected int side2;

    public Square(int x, int y, int side1, int side2) {
        this.x = x;
        this.y = y;
        this.side1 = side1;
        this.side2 = side2;
    }

    static class BlackSquare extends Square {

        public BlackSquare(int x, int y, int side1, int side2) {
            super(x, y, side1, side2);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.black);
            g2d.drawRect(this.x, this.y, this.side1, this.side2);

            g2d.setColor(Color.black);
            g2d.fillRect(this.x, this.y, this.side1, this.side2);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.drawRect(this.x, this.y, this.side1, this.side2);

        g2d.setColor(Color.white);
        g2d.fillRect(this.x, this.y, this.side1, this.side2);
    }
}