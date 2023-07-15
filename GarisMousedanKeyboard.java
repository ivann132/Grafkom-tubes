import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GarisMousedanKeyboard extends JFrame {
    private Point start;
    private Point end;

    public GarisMousedanKeyboard() {
        setTitle("Garis Mouse");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (start == null) {
                    start = e.getPoint();
                } else if (end == null) {
                    end = e.getPoint();
                    drawLine(start, end);
                    start = null;
                    end = null;
                }
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
                        drawLine(start, end);
                        start = null;
                        end = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Input tidak valid!");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    repaint();
                    ;
                }
            }

        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();

        g.drawLine(50, height - 50, width - 50, height - 50);
        g.drawLine(50, height - 50, 50, 50);

        g.drawString("Sumbu X ", 250, 470);
        g.drawString("Sumbu Y ", 10, 250);
    }

    public void setPixel(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void drawLine(Point p1, Point p2) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        System.out.println("(" + p1.getX() + "," + p1.getY() + ") " + "(" + p2.getX() + "," + p2.getY() + ")");

        while (true) {
            setPixel(x1, y1);
            if (x1 == x2 && y1 == y2) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
            System.out.println("(" + x1 + "," + y1 + ")");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GarisMousedanKeyboard garisMouse = new GarisMousedanKeyboard();
            garisMouse.setVisible(true);
        });
    }
}
