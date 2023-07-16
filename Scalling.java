import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class Scalling extends Frame implements ActionListener {
    private Point shapeSize;
    private Point shape;
    private double scale = 1.0;

    private Button scaleUpButton;
    private Button scaleDownButton;

    public Scalling() {
        setSize(400, 400);
        setTitle("Affine Transformation - Scaling");
        setLayout(new FlowLayout());
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = JOptionPane
                            .showInputDialog("Masukkan koordinat X1,Y1,lebar,tinggi (pisahkan dengan koma):");
                    String[] coordinates = input.split(",");
                    if (coordinates.length == 4) {
                        shape = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                        shapeSize = new Point(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Input tidak valid!");
                    }
                }
            }

        });

        scaleUpButton = new Button("Scale Up");
        scaleUpButton.addActionListener(this);
        add(scaleUpButton);

        scaleDownButton = new Button("Scale Down");
        scaleDownButton.addActionListener(this);
        add(scaleDownButton);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Calculate the scaled size of the shape
        int scaledSizex = (int) (shapeSize.x * scale);
        int scaledSizey = (int) (shapeSize.x * scale);

        // Calculate the position of the scaled shape
        int scaledX = shape.x - (scaledSizex - shapeSize.x) / 2;
        int scaledY = shape.y - (scaledSizey - shapeSize.y) / 2;

        // Draw the shape
        g2d.setColor(Color.BLUE);
        g2d.fillRect(scaledX, scaledY, scaledSizex, scaledSizey);

        // Output the coordinate details
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, (int) (12 * scale)));
        int textOffset = (int) (15 * scale);
        g2d.drawString(": (" + scaledX + ", " + scaledY + ")", scaledX, scaledY - textOffset);
        g2d.drawString(": (" + (scaledX + scaledSizex) + ", " + scaledY + ")", scaledX + scaledSizex,
                scaledY - textOffset);
        g2d.drawString(": (" + scaledX + ", " + (scaledY + scaledSizey) + ")", scaledX,
                scaledY + scaledSizey + textOffset);
        g2d.drawString(": (" + (scaledX + scaledSizex) + ", " + (scaledY + scaledSizey) + ")", scaledX + scaledSizex,
                scaledY + scaledSizey + textOffset);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == scaleUpButton) {
            scale *= 1.1; // Increase scale by 10%
        } else if (e.getSource() == scaleDownButton) {
            scale *= 0.9; // Decrease scale by 10%
        }

        // Trigger a repaint to update the scaling
        repaint();
    }

    public static void main(String[] args) {
        Scalling app = new Scalling();
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
