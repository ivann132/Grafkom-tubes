import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowingExample {
    private JFrame mainWindow;
    private JFrame halfWindow;
    private Point startPoint;
    private Point endPoint;
    private boolean isDrawing;

    public WindowingExample() {
        mainWindow = new JFrame();
        mainWindow.setTitle("Main Window");
        mainWindow.setSize(500, 500);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);

        halfWindow = new JFrame();
        halfWindow.setTitle("Half Window");
        halfWindow.setSize(250, 500);
        halfWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        halfWindow.setLocationRelativeTo(null);

        isDrawing = false;

        JPanel drawingPanel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (isDrawing) {
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                }
            }
        };

        JPanel drawingPanel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (isDrawing) {
                    int halfWidth = getWidth() / 2;
                    int halfHeight = getHeight() / 2;
                    g.setClip(0, 0, halfWidth, halfHeight);
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                }
            }
        };

        drawingPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                isDrawing = true;
                drawingPanel1.repaint();
                drawingPanel2.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                isDrawing = false;
                drawingPanel1.repaint();
                drawingPanel2.repaint();
            }
        });

        mainWindow.getContentPane().add(drawingPanel1);
        halfWindow.getContentPane().add(drawingPanel2);
    }

    public void showWindows() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainWindow.setVisible(true);
                halfWindow.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        WindowingExample example = new WindowingExample();
        example.showWindows();
    }
}
