import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Scaling2 extends Frame implements ActionListener, MouseListener, MouseMotionListener {
    private int shapeSize = 70;
    private int shapeX;
    private int shapeY;
    private int initialShapeX;
    private int initialShapeY;
    private double shearX = 0.0;
    private double shearY = 0.0;
    private boolean isDrawing = false;
    // ButtonShear
    private Button shearRightButton;
    private Button shearLeftButton;

    public Scaling2() {
        setSize(400, 400);
        setTitle("Manual Shearing");
        setLayout(new FlowLayout());
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        shearRightButton = new Button("Shear Right");
        shearRightButton.addActionListener(this);
        add(shearRightButton);

        shearLeftButton = new Button("Shear Left");
        shearLeftButton.addActionListener(this);
        add(shearLeftButton);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Membersihkan hasil transformasi sebelumnya
        g2d.setTransform(new AffineTransform());

        // Manual Shearing
        g2d.shear(shearX, shearY);

        // Melakukan draw bangun dalam suatu posisi
        g2d.setColor(Color.BLUE);
        g2d.fillRect(shapeX, shapeY, shapeSize, shapeSize);

        // Penulisan text koordinat
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        int textOffset = 15;
        g2d.drawString(": (" + shapeX + ", " + shapeY + ")", shapeX, shapeY - textOffset);
        g2d.drawString(": (" + (shapeX + shapeSize) + ", " + shapeY + ")", shapeX + shapeSize, shapeY - textOffset);
        g2d.drawString(": (" + shapeX + ", " + (shapeY + shapeSize) + ")", shapeX, shapeY + shapeSize + textOffset);
        g2d.drawString(": (" + (shapeX + shapeSize) + ", " + (shapeY + shapeSize) + ")", shapeX + shapeSize,
                shapeY + shapeSize + textOffset);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shearRightButton) {
            shearX += 0.1; // Increase shear factor berdasarkan sumbu x
        } else if (e.getSource() == shearLeftButton) {
            shearX -= 0.1; // Decrease shear factor berdasarkan sumbu X
        }
        // Melakukan repaint apabila ada update skala
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (!isDrawing) {
            // isDrawing start
            initialShapeX = e.getX();
            initialShapeY = e.getY();
            shapeX = initialShapeX;
            shapeY = initialShapeY;
            isDrawing = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isDrawing) {
            // Melakukan hasil akhir dari bangun
            int finalShapeX = e.getX();
            int finalShapeY = e.getY();
            shapeX = Math.min(initialShapeX, finalShapeX);
            shapeY = Math.min(initialShapeY, finalShapeY);
            int shapeWidth = Math.abs(finalShapeX - initialShapeX);
            int shapeHeight = Math.abs(finalShapeY - initialShapeY);
            shapeSize = Math.min(shapeWidth, shapeHeight);
            shearX = 0.0;
            shearY = 0.0;
            isDrawing = false;
            repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        if (isDrawing) {
            // Update ukuran berdasarkan mouse
            int currentShapeX = e.getX();
            int currentShapeY = e.getY();
            int shapeWidth = Math.abs(currentShapeX - initialShapeX);
            int shapeHeight = Math.abs(currentShapeY - initialShapeY);
            shapeSize = Math.min(shapeWidth, shapeHeight);
            shapeX = Math.min(currentShapeX, initialShapeX);
            shapeY = Math.min(currentShapeY, initialShapeY);
            repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {
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
