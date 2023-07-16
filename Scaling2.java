import java.awt.*;
import java.awt.event.*;

public class Scaling2 extends Frame implements ActionListener {
    private int shapeSize = 100;
    private int shapeX = 150;
    private int shapeY = 150;
    private double scaleX = 1.0;
    private double scaleY = 1.0;

    private Button upButton;
    private Button downButton;
    private Button rightButton;
    private Button leftButton;

    public Scaling2() {
        setSize(400, 400);
        setTitle("Affine Transformation - Scaling");
        setLayout(new FlowLayout());
        setVisible(true);

        upButton = new Button("Up");
        upButton.addActionListener(this);
        add(upButton);

        downButton = new Button("Down");
        downButton.addActionListener(this);
        add(downButton);

        rightButton = new Button("Right");
        rightButton.addActionListener(this);
        add(rightButton);

        leftButton = new Button("Left");
        leftButton.addActionListener(this);
        add(leftButton);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Calculate the scaled size of the shape
        int scaledWidth = (int) (shapeSize * scaleX);
        int scaledHeight = (int) (shapeSize * scaleY);

        // Calculate the position of the scaled shape
        int scaledX = shapeX - scaledWidth / 2;
        int scaledY = shapeY - scaledHeight / 2;

        // Draw the shape
        g2d.setColor(Color.RED);
        g2d.fillRect(scaledX, scaledY, scaledWidth, scaledHeight);

        // Output the coordinate details on the edges of the shape
        g2d.setColor(Color.BLACK);
        g2d.drawString("(" + scaledX + ", " + scaledY + ")", scaledX, scaledY);
        g2d.drawString("(" + (scaledX + scaledWidth) + ", " + scaledY + ")", scaledX + scaledWidth, scaledY);
        g2d.drawString("(" + scaledX + ", " + (scaledY + scaledHeight) + ")", scaledX, scaledY + scaledHeight);
        g2d.drawString("(" + (scaledX + scaledWidth) + ", " + (scaledY + scaledHeight) + ")", scaledX + scaledWidth,
                scaledY + scaledHeight);
    }

    public void actionPerformed(ActionEvent e) {
        double scaleChange = 0.1;

        if (e.getSource() == upButton) {
            scaleY += scaleChange; // Increase vertical scale (scale up)
        } else if (e.getSource() == downButton) {
            scaleY -= scaleChange; // Decrease vertical scale (scale down)
            if (scaleY < scaleChange) {
                scaleY = scaleChange; // Minimum vertical scale
            }
        } else if (e.getSource() == rightButton) {
            scaleX += scaleChange; // Increase horizontal scale (scale up)
        } else if (e.getSource() == leftButton) {
            scaleX -= scaleChange; // Decrease horizontal scale (scale down)
            if (scaleX < scaleChange) {
                scaleX = scaleChange; // Minimum horizontal scale
            }
        }

        // Trigger a repaint to update the scaling
        repaint();
    }

    public static void main(String[] args) {
        Scaling2 app = new Scaling2();
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
