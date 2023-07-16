import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClippingResultFrame extends Frame {
    private Point start, end;
    private Point clickedPoint;

    public ClippingResultFrame(Point start, Point end, Point clickedPoint) {
        this.start = start;
        this.end = end;
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
        g.drawLine(start.x, start.y, end.x, end.y);

        // Draw the clipping rectangle if a point is clicked
        if (clickedPoint != null) {
            g.setColor(Color.RED);
            g.drawLine(0, clickedPoint.y, getWidth(), clickedPoint.y);
            g.drawLine(clickedPoint.x, 0, clickedPoint.x, getHeight());

            // Clip the line
            g.clipRect(0, 0, clickedPoint.x, clickedPoint.y);

            // Draw the clipped line
            g.setColor(Color.BLUE);
            g.drawLine(start.x, start.y, end.x, end.y);
        }
    }
}
