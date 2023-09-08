import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grid extends JPanel implements MouseListener {
    public Lista<Lista<Cuadrante>> matriz;
    public Lista<Cuadrante> r1,r2,r3,r4,r5,r6,r7,r8,r9;
    public Cuadrante marca;
    public int marcaPos;
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
            System.out.print("\n");
        }

        marca=matriz.getHead().getData().getHead().getData();
        marcaPos=1;
        System.out.println(String.format("%d %d %d",marca.col,marca.row,marcaPos));
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
                if (cuad.marked==cuad.top) {
                    g2d.drawLine(cuad.top.getX0(), cuad.top.getY0(), cuad.top.getX1(), cuad.top.getY1());
                } if (cuad.marked==cuad.bot) {
                    g2d.drawLine(cuad.bot.getX0(), cuad.bot.getY0(), cuad.bot.getX1(), cuad.bot.getY1());
                } if (cuad.marked==cuad.left) {
                    g2d.drawLine(cuad.left.getX0(), cuad.left.getY0(), cuad.left.getX1(), cuad.left.getY1());
                } if (cuad.marked==cuad.right) {
                    g2d.drawLine(cuad.right.getX0(), cuad.right.getY0(), cuad.right.getX1(), cuad.right.getY1());

                }
            }
        }

    }
    public void mark(Cuadrante cuad,int side, Color color){
        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                matriz.getNodo(i-1).getData().getNodo(j-1).getData().marked=null;
            }
        }
        switch (side){
            case 1:
                cuad.marked=cuad.left;
                break;
            case 2:
                cuad.marked=cuad.top;
                break;
            case 3:
                cuad.marked=cuad.bot;
                break;
            case 4:
                cuad.marked=cuad.right;
                break;
        }
        drawLine(cuad.marked, color);
//        this.repaint();


    }
    public void drawLine(Linea linea, Color color) {
        Graphics g = getGraphics();
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(color);
            g2d.setStroke(new BasicStroke(6));
            g2d.drawLine(linea.getX0(), linea.getY0(), linea.getX1(), linea.getY1());
        }

    }
    /*
    w       87
    a       65
    s       83
    d       68
    up      38
    left    37
    down    40
    right   39
    enter   10
    space   32
     */
    public void navegar(int key){
        switch (key){
            case 87: case 38:
//                System.out.println("Arriba");
                if (marcaPos==1){
                    marcaPos=2;
                } else if (marca.row!=1) {
                    marcaPos=1;
                    marca=matriz.getNodo(marca.row-2).getData().getNodo(marca.col-1).getData();
                }
                break;
            case 65: case 37:
//                System.out.println("Izquierda");
                if (marca.col!=1){
                    marca=(Cuadrante) marca.getPrev();
                }
                break;
            case 83: case 40:
//                System.out.println("Abajo");
                if (marcaPos==2){
                    marcaPos=1;
                } else if (marca.row!=9) {
                    marcaPos=2;
                    marca=matriz.getNodo(marca.row).getData().getNodo(marca.col-1).getData();
                }
                break;
            case 68: case 39:
//                System.out.println("Derecha");
                if (marca.col!=9){
                    marca.getCoords();
                    System.out.println(marca);
                    marca= (Cuadrante) marca.getNext();
                    System.out.println(marca);
                    //marca.getCoords();
                }
                break;
            case 10: case 32:
//                System.out.println("Seleccionar");
                break;
        }
        //System.out.println(String.format("%d %d %d",marca.col,marca.row,marcaPos));
        mark(marca,marcaPos,Color.CYAN);

    }
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
