import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class ClippingResultFrame extends Frame {

    private List<Point> vertices;
    private Point clickedPoint;

    public ClippingResultFrame(List<Point> vertices, Point clickedPoint) {
        this.vertices = vertices;
        this.clickedPoint = clickedPoint;

        setTitle("Clipping Result");
        setSize(400, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
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

            // Clip the line
            g.clipRect(0, 0, clickedPoint.x, clickedPoint.y);

            // Draw the clipped line
            g.setColor(Color.BLUE);
            for (int i = 0; i < vertices.size() - 1; i++) {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get(i + 1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}
