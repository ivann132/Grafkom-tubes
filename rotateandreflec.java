import java.awt.*;
import java.awt.event.*;

public class rotateandreflec extends Frame implements MouseListener {

    private Point start;
    private Point end;

    public rotateandreflec() {
        start = null;
        end = null;

        setTitle("Interactive Line Plot");
        setSize(400, 400);
        setVisible(true);

        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        if (start != null && end != null) {
            // Draw the original line
            g.setColor(Color.BLUE);
            g.drawLine(start.x, start.y, end.x, end.y);

            // Reflect the line
            int x = end.x;
            int reflectedStartX = 2 * x - start.x;
            int reflectedEndX = 2 * x - end.x;
            Point reflectedStart = new Point(reflectedStartX, start.y);
            Point reflectedEnd = new Point(reflectedEndX, end.y);
            g.setColor(Color.RED);
            g.drawLine(reflectedStart.x, reflectedStart.y, reflectedEnd.x, reflectedEnd.y);

            // Rotate the line
            double angle = Math.PI / 4; // Example rotation angle of 45 degrees (pi/4 radians)
            Point rotatedStart = rotatePoint(start, angle, end);
            Point rotatedEnd = rotatePoint(end, angle, end);
            g.setColor(Color.GREEN);
            g.drawLine(rotatedStart.x, rotatedStart.y, rotatedEnd.x, rotatedEnd.y);

            // Print the line coordinates
            System.out.println("Line Coordinates:");
            System.out.println("Start Pos: (" + start.x + ", " + start.y + ")");
            System.out.println("End Pos: (" + end.x + ", " + end.y + ")");
            System.out.println();

            // Print the reflected coordinates
            System.out.println("Reflected Line Coordinates:");
            System.out.println("Reflected Start Pos: (" + reflectedStart.x + ", " + reflectedStart.y + ")");
            System.out.println("Reflected End Pos: (" + reflectedEnd.x + ", " + reflectedEnd.y + ")");
            System.out.println();

            // Print the rotated coordinates
            System.out.println("Rotated Line Coordinates:");
            System.out.println("Rotated Start Pos: (" + rotatedStart.x + ", " + rotatedStart.y + ")");
            System.out.println("Rotated End Pos: (" + rotatedEnd.x + ", " + rotatedEnd.y + ")");
            System.out.println();
        }
    }

    private Point rotatePoint(Point point, double angle, Point center) {
        int translatedX = point.x - center.x;
        int translatedY = point.y - center.y;
        int rotatedX = (int) (translatedX * Math.cos(angle) - translatedY * Math.sin(angle));
        int rotatedY = (int) (translatedX * Math.sin(angle) + translatedY * Math.cos(angle));
        return new Point(rotatedX + center.x, rotatedY + center.y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (start == null) {
            start = e.getPoint();
        } else if (end == null) {
            end = e.getPoint();
        } else {
            start = null;
            end = null;
        }
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
        rotateandreflec plot = new rotateandreflec();
    }
}
