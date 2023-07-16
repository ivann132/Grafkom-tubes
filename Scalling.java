import java.awt.*;
import java.awt.event.*;

public class Scalling extends Frame implements ActionListener {
    private int shapeSize = 70;
    private int shapeX = 100;
    private int shapeY = 120;
    private double scale = 1.0;

    private Button scaleUpButton;
    private Button scaleDownButton;

    public Scalling() {
        setSize(400, 400);
        setTitle("Affine Transformation - Scaling");
        setLayout(new FlowLayout());
        setVisible(true);

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
        int scaledSize = (int) (shapeSize * scale);

        // Calculate the position of the scaled shape
        int scaledX = shapeX - (scaledSize - shapeSize) / 2;
        int scaledY = shapeY - (scaledSize - shapeSize) / 2;

        // Draw the shape
        g2d.setColor(Color.BLUE);
        g2d.fillRect(scaledX, scaledY, scaledSize, scaledSize);

        // Output the coordinate details
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, (int) (12 * scale)));
        int textOffset = (int) (15 * scale);
        g2d.drawString(": (" + scaledX + ", " + scaledY + ")", scaledX, scaledY - textOffset);
        g2d.drawString(": (" + (scaledX + scaledSize) + ", " + scaledY + ")", scaledX + scaledSize,
                scaledY - textOffset);
        g2d.drawString(": (" + scaledX + ", " + (scaledY + scaledSize) + ")", scaledX,
                scaledY + scaledSize + textOffset);
        g2d.drawString(": (" + (scaledX + scaledSize) + ", " + (scaledY + scaledSize) + ")", scaledX + scaledSize,
                scaledY + scaledSize + textOffset);
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
