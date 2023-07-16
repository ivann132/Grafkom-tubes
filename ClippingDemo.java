import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ClippingDemo extends Frame {
    private static Point start, end;
    private static Point clickedPoint;

    public ClippingDemo() {
        start = null;
        end = null;
        clickedPoint = null;

        // Set up the frame
        setTitle("Interactive Plot");
        setSize(400, 400);
        setVisible(true);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clickedPoint = e.getPoint();
                repaint();

                ClippingResultFrame resultFrame = new ClippingResultFrame(start, end, clickedPoint);
                resultFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent windowEvent) {
                        clickedPoint = null;
                        repaint();
                    }
                });
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = JOptionPane
                            .showInputDialog("Masukkan koordinat X1,Y1,X2,Y2 (pisahkan dengan koma):");
                    String[] coordinates = input.split(",");
                    if (coordinates.length == 4) {
                        start = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                        end = new Point(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Input tidak valid!");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    repaint();
                }
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
        }

        // Draw the grid lines based on the clipping rectangle
        if (clickedPoint != null) {
            g.setColor(Color.GRAY);
            int num = 5; // Adjust the grid size here
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

    public static void main(String[] args) {
        ClippingDemo plot = new ClippingDemo();
    }
}
