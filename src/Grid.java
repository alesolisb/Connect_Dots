import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Grid extends JPanel implements Serializable{
    public Lista<Lista<Cuadrante>> matriz;
    public Nodo<Cuadrante> marca;
    public Nodo<Lista<Cuadrante>> marcaRow;
    public int marcaPos;
    Grid(int x, int y, int width, int height){
        this.setLayout(new GridLayout(10,10));
        this.setBounds(x,y,width,height);
        this.setBackground(Color.white);
        matriz = new Lista<>();
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());
        matriz.insertLast(new Lista<>());

        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                Lista<Cuadrante> row = matriz.getNodo(i-1).getData();
                row.insertLast(new Cuadrante(i,j));
                //System.out.print(matriz.getNodo(i-1).getData().getNodo(j-1).getData().getCoords()+" ");
            }
            //System.out.print("\n\n");
        }

        /*for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 9; j++) {
                System.out.print(matriz.getNodo(i-1).getData().getNodo(j-1).getNext().getData().getCoords()+" ");
            }
            System.out.print("\n");
        }

        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 2; j < 10; j++) {
                System.out.print(matriz.getNodo(i-1).getData().getNodo(j-1).getPrev().getData().getCoords()+" ");
            }
            System.out.print("\n");
        }*/
        marcaRow = matriz.getHead();
        marca = marcaRow.getData().getHead();
        marcaPos = 1;

        /*for (int i=1; i<(matriz.getSize()+1);i++){
            marca= marcaRow.getData().getHead();
            for (int j = 1; j < 10; j++) {
                System.out.print(marca.getData().getCoords() + "  ");
                marca=marca.getNext();
            }
            System.out.print("\n");
            marcaRow = marcaRow.getNext();

        }*/

        //System.out.println(String.format("%d %d %d",marca.getData().getCol(),marca.getData().getRow(),marcaPos));
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaintMode();
        g2d.setBackground(Color.WHITE);
        g2d.setPaint(Color.BLUE);
        g2d.setStroke(new BasicStroke(6));
        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                Cuadrante cuad = matriz.getNodo(i-1).getData().getNodo(j-1).getData();
                //Cuadrante cuad = marca.getData();
                if (cuad.getTop().isOwned()) {
                    g2d.drawLine(cuad.top.getX0(), cuad.top.getY0(), cuad.top.getX1(), cuad.top.getY1());
                } if (cuad.getBot().isOwned()) {
                    g2d.drawLine(cuad.bot.getX0(), cuad.bot.getY0(), cuad.bot.getX1(), cuad.bot.getY1());
                } if (cuad.getLeft().isOwned()) {
                    g2d.drawLine(cuad.left.getX0(), cuad.left.getY0(), cuad.left.getX1(), cuad.left.getY1());
                } if (cuad.getRight().isOwned()) {
                    g2d.drawLine(cuad.right.getX0(), cuad.right.getY0(), cuad.right.getX1(), cuad.right.getY1());
                }
            }
        }
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(0,0,WIDTH,HEIGHT);
        for (int i=1; i<=10;i++){
            for (int j = 1; j <= 10; j++) {
                g2d.fillRect((80*j),(80*i),6,6);
            }
        }

        g2d.dispose();

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
        //drawLine(cuad.marked, color);
        paintComponent(this.getGraphics());
        update(this.getGraphics());


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Linea linea = marca.getData().getMarked();
        Graphics2D g2d =(Graphics2D) this.getGraphics();
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawLine(linea.getX0(), linea.getY0(), linea.getX1(), linea.getY1());

    }
    public void drawLine(Linea linea, Color color) {
        Graphics g = getGraphics();
        if (g instanceof Graphics2D g2d) {
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
    public void navegar(int key) {

        switch (key) {
            case 87, 38 -> {
//                System.out.println("Arriba");
                if ((marca.getData().getRow() >= 1)) {
                    if ((marcaPos == 4) && (marca.getData().getRow() > 1) && (marca.getData().getCol() == 9)) {
                        marcaRow = marcaRow.getPrev();
                        int marcaCol = marca.getData().getCol();
                        marca = marcaRow.getData().getNodo(marcaCol - 1);
                    } else if (marcaPos != 2) {
                        marcaPos = 2;
                    } else if (marca.getData().getRow() == 1) {
                        marcaPos = 2;
                    } else if (marcaPos != 1) {
                        marcaPos = 1;
                        marcaRow = marcaRow.getPrev();
                        int marcaCol = marca.getData().getCol();
                        marca = marcaRow.getData().getNodo(marcaCol - 1);
                    }
                }
            }
            case 65, 37 -> {
//                System.out.println("Izquierda");
                if (marcaPos == 4) {
                    marcaPos = 1;
                } else if (marca.getData().getCol() > 1) {
                    marca = marca.getPrev();

                }
            }
            case 83, 40 -> {
//                System.out.println("Abajo");
                if ((marca.getData().getRow() <= 9)) {
                    if ((marcaPos == 4) && (marca.getData().getRow() < 9) && (marca.getData().getCol() == 9)) {
                        marcaRow = marcaRow.getNext();
                        int marcaCol = marca.getData().getCol();
                        marca = marcaRow.getData().getNodo(marcaCol - 1);
                    } else if (marcaPos != 1) {
                        marcaPos = 1;
                    } else if (marca.getData().getRow() == 9) {
                        marcaPos = 3;
                    } else if (marcaPos != 2) {
                        marcaPos = 2;
                        marcaRow = marcaRow.getNext();
                        int marcaCol = marca.getData().getCol();
                        marca = marcaRow.getData().getNodo(marcaCol - 1);
                    }
                }
            }
            case 68, 39 -> {
//                System.out.println("Derecha");
                if (marca.getData().getCol() < 9) {
                    marca = marca.getNext();
                } else if (marca.getData().getCol() == 9) {
                    marcaPos = 4;
                }
            }
            case 10, 32 -> {
                //System.out.println("Seleccionar");
                Cuadrante cuad = marca.getData();
                switch (marcaPos) {
                    case 1 -> cuad.getLeft().setOwned(true);
                    case 2 -> cuad.getTop().setOwned(true);
                    case 3 -> cuad.getBot().setOwned(true);
                    case 4 -> cuad.getRight().setOwned(true);
                }

//                marca.getData().getMarked();
            }
//
        }
        //System.out.println(String.format("%d %d %d",marca.col,marca.row,marcaPos));
        mark(marca.getData(), marcaPos, Color.CYAN);
    }

}
