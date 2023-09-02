import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grid extends JPanel implements MouseListener {
    public Lista<Lista<Cuadrante>> matriz;
    public Lista<Cuadrante> r1,r2,r3,r4,r5,r6,r7,r8,r9;
    Grid(int x, int y, int width, int height){
        this.setLayout(new GridLayout(10,10));
        this.setBounds(x,y,width,height);
        this.setBackground(Color.white);
        this.addMouseListener(this);
        matriz = new Lista<>();
        matriz.insertLast(r1 = new Lista<>());
        matriz.insertLast(r2 = new Lista<>());
        matriz.insertLast(r3 = new Lista<>());
        matriz.insertLast(r4 = new Lista<>());
        matriz.insertLast(r5 = new Lista<>());
        matriz.insertLast(r6 = new Lista<>());
        matriz.insertLast(r7 = new Lista<>());
        matriz.insertLast(r8 = new Lista<>());
        matriz.insertLast(r9 = new Lista<>());

        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                Lista<Cuadrante> row = matriz.getNodo(i-1).getData();
                row.insertLast(new Cuadrante(i,j));
                System.out.print(matriz.getNodo(i-1).getData().getNodo(j-1).getData().getCoords());
            }
            System.out.println("\n");
        }
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1));
        for (int i=1; i<=10;i++){
            for (int j = 1; j <= 10; j++) {
                g2d.fillRect((80*j),(80*i),6,6);
            }
        }
        g2d.setPaint(Color.BLUE);
        g2d.setStroke(new BasicStroke(6));
        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                Cuadrante cuad = matriz.getNodo(i-1).getData().getNodo(j-1).getData();
                if (cuad.isOwned()) {
                    g2d.drawLine(cuad.top.getX0(), cuad.top.getY0(), cuad.top.getX1(), cuad.top.getY1());
                    g2d.drawLine(cuad.bot.getX0(), cuad.bot.getY0(), cuad.bot.getX1(), cuad.bot.getY1());
                    g2d.drawLine(cuad.left.getX0(), cuad.left.getY0(), cuad.left.getX1(), cuad.left.getY1());
                    g2d.drawLine(cuad.right.getX0(), cuad.right.getY0(), cuad.right.getX1(), cuad.right.getY1());
                }
            }

        }
    }
    /*public void drawLine(Linea linea, Color color){
        Graphics2D g2d = (Graphics2D)
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1));
    }*/

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("("+e.getX()+","+e.getY()+")");

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
