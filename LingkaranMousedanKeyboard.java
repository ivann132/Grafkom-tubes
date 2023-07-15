import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LingkaranMousedanKeyboard extends JFrame {
    private Point center;
    private int radius;

    public LingkaranMousedanKeyboard() {
        setTitle("Garis Mouse");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (center == null) {
                    center = e.getPoint();
                } else {
                    int x = Math.abs(e.getX() - center.x);
                    int y = Math.abs(e.getY() - center.y);
                    radius = (int) Math.sqrt(x * x + y * y);
                    drawLine(center, radius);
                    center = null;
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = JOptionPane
                            .showInputDialog("Masukkan koordinat X,Y dan radius (pisahkan dengan koma):");
                    String[] coordinates = input.split(",");
                    if (coordinates.length == 3) {
                        center = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                        radius = (Integer.parseInt(coordinates[2]));
                        drawLine(center, radius);
                        center = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Input tidak valid!");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    repaint();
                }
            }

        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void setPixel(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void drawLine(Point center, int radius) {
        int x = 0;
        int y = radius;
        int p = 3 - 2 * radius;

        System.out.println("(" + center.x + "," + center.y + ") " + "(" + x + "," + y + ")");

        while (x < y) {
            drawCirclePoints(center.x, center.y, x, y);
            if (p < 0) {
                p += 4 * x + 6;
            } else {
                p += 4 * (x - y) + 10;
                y--;
            }
            x++;
            System.out.println("(" + x + "," + y + ") ");
        }
    }

    private void drawCirclePoints(int cx, int cy, int x, int y) {
        setPixel(cx + x, cy + y);
        setPixel(cx - x, cy + y);
        setPixel(cx + x, cy - y);
        setPixel(cx - x, cy - y);
        setPixel(cx + y, cy + x);
        setPixel(cx - y, cy + x);
        setPixel(cx + y, cy - x);
        setPixel(cx - y, cy - x);
    }

    // public void paint(Graphics g) {
    // g.setColor(Color.BLACK);
    // g.fillRect(0, 0, WIDTH, HEIGHT);
    // }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LingkaranMousedanKeyboard Lingkaran = new LingkaranMousedanKeyboard();
            Lingkaran.setVisible(true);
        });
    }
}
