package graphics;

import logic.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Renderer extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircle(g);
    }

    private void drawCircle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, 100, 100);
        g2d.fill(circle);
    }

    public void draw(GameState gameState) {
    }
}
