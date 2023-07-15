import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class windowandclip extends Frame implements MouseListener {

    private List<Point> vertices;
    private Point clickedPoint;

    public windowandclip() {
        vertices = new ArrayList<>();
        clickedPoint = null;

        // Add vertices to the line
        vertices.add(new Point(0, 0));
        vertices.add(new Point(250, 250));

        // Set up the frame
        setTitle("Interactive Plot");
        setSize(400, 400);
        setVisible(true);

        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        // Clear the previous drawings
        g.clearRect(0, 0, getWidth(), getHeight());

        // Draw the line
        g.setColor(Color.BLACK);
        for (int i = 0; i < vertices.size() - 1; i++) {
            Point p1 = vertices.get(i);
            Point p2 = vertices.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw the clipping rectangle if a point is clicked
        if (clickedPoint != null) {
            g.setColor(Color.RED);
            g.drawLine(0, clickedPoint.y, getWidth(), clickedPoint.y);
            g.drawLine(clickedPoint.x, 0, clickedPoint.x, getHeight());
        }

        // Draw the grid lines based on the clipping rectangle
        if (clickedPoint != null) {
            g.setColor(Color.GRAY);
            int num = 5;  // Adjust the grid size here
            int deltaX = getWidth() / (num - 1);
            int deltaY = getHeight() / (num - 1);

            for (int i = 0; i < num; i++) {
                int gx = i * deltaX;
                int gy = i * deltaY;
                g.drawLine(gx, 0, gx, getHeight());
                g.drawLine(0, gy, getWidth(), gy);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickedPoint = e.getPoint();
        repaint();
    }

    // Unused event handlers
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        windowandclip plot = new windowandclip();
    }
}
