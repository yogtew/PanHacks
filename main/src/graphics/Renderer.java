package graphics;

import logic.GameState;
import javax.swing.*;
import java.awt.*;
import logic.Player;
import java.awt.geom.Ellipse2D;

public class Renderer extends JPanel {

    Graphics graphics;
    GameState gameState;

    public Renderer(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Maze maze = new Maze();
        this.graphics = g;
        // drawCircle(g);
        draw();
    }

    private void drawCircle(float x, float y, float w, float h) {
        Graphics2D g2d = (Graphics2D) graphics;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
        g2d.fill(circle);
    }

    public void draw() {
        for (Player p:gameState.getPlayers()) {
            float x = p.center.x;
            float y = p.center.y;
            float r = p.radius;
            drawCircle(x, y, r, r);
        }
    }
}
