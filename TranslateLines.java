import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TranslateLines extends JFrame {
    private ArrayList<Point> coordinates = new ArrayList<>();
    private Point translationOffset = new Point(0, 0);
    private JPanel canvasPanel;
    private JButton translateButton1, translateButton2, translateButton3, translateButton4;

    public TranslateLines() {
        setTitle("Translate Lines");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLines(g);
            }
        };
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });

        translateButton1 = new JButton("Translate Up");
        translateButton2 = new JButton("Translate Down");
        translateButton3 = new JButton("Translate Left");
        translateButton4 = new JButton("Translate Right");

        translateButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translate(0, -40);
            }
        });

        translateButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translate(0, 40);
            }
        });

        translateButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translate(-40, 0);
            }
        });

        translateButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translate(40, 0);
            }
        });

        JLabel blackLabel = new JLabel("black: original line");
        JLabel redLabel = new JLabel("red: translated line");
        redLabel.setForeground(Color.RED);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(translateButton1);
        buttonPanel.add(translateButton2);
        buttonPanel.add(translateButton3);
        buttonPanel.add(translateButton4);

        JPanel labelPanel = new JPanel();
        labelPanel.add(blackLabel);
        labelPanel.add(redLabel);

        JPanel controlPanel = new JPanel();
        controlPanel.add(buttonPanel);
        controlPanel.add(labelPanel);

        getContentPane().add(canvasPanel);
        getContentPane().add(controlPanel, "South");
    }

    private void drawLines(Graphics g) {
        for (int i = 1; i < coordinates.size(); i++) {
            Point startPoint = coordinates.get(i - 1);
            Point endPoint = coordinates.get(i);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

            Point translatedStartPoint = translateLine(startPoint, translationOffset);
            Point translatedEndPoint = translateLine(endPoint, translationOffset);
            g.setColor(Color.RED);
            g.drawLine(translatedStartPoint.x, translatedStartPoint.y, translatedEndPoint.x, translatedEndPoint.y);

            g.setColor(Color.BLACK);
            g.drawString("(" + startPoint.x + ", " + startPoint.y + ")", startPoint.x, startPoint.y);
            g.drawString("(" + endPoint.x + ", " + endPoint.y + ")", endPoint.x, endPoint.y);
            g.drawString("(" + translatedStartPoint.x + ", " + translatedStartPoint.y + ")", translatedStartPoint.x,
                    translatedStartPoint.y);
            g.drawString("(" + translatedEndPoint.x + ", " + translatedEndPoint.y + ")", translatedEndPoint.x,
                    translatedEndPoint.y);
        }
    }

    private void handleMouseClick(MouseEvent e) {
        coordinates.add(e.getPoint());
        canvasPanel.repaint();
    }

    private Point translateLine(Point point, Point offset) {
        return new Point(point.x + offset.x, point.y + offset.y);
    }

    private void translate(int dx, int dy) {
        translationOffset.setLocation(dx, dy);
        if (dx == 0 && dy < 0) {
            System.out.println("Translate Up");
        } else if (dx == 0 && dy > 0) {
            System.out.println("Translate Down");
        } else if (dx < 0 && dy == 0) {
            System.out.println("Translate Left");
        } else if (dx > 0 && dy == 0) {
            System.out.println("Translate Right");
        }
        canvasPanel.repaint();
    }

    public static void main(String[] args) {
        TranslateLines app = new TranslateLines();
        app.setVisible(true);
    }
}
