import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grid extends JPanel{
    public Lista<Lista<Cuadrante>> matriz;
    public Nodo<Cuadrante> marca;
    public Nodo<Lista<Cuadrante>> marcaRow;
    public int marcaPos;
    public Graphics2D g2d;
    Cola cola;
    Player turno, p1, p2, p3;
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
            }
        }

        marcaRow = matriz.getHead();
        marca = marcaRow.getData().getHead();
        marcaPos = 1;

        p1 = new Player("UNO", new Color(0, 132, 236),new Color(0, 132, 236,100));
        p2 = new Player("DOS", new Color(255, 145,0),new Color(255, 145,0,100));
        p3 = new Player("TRES", new Color(140,0, 255),new Color(140,0,255, 100));

        cola = new Cola();

        cola.enqueue(p1);
        cola.enqueue(p2);
        cola.enqueue(p3);
        turno=cola.getHead().getData();

    }
    @Override
    public void paint(Graphics g){
//        System.out.println("Update");
        this.g2d = (Graphics2D) g;
        this.g2d.setPaintMode();
        this.g2d.setBackground(Color.WHITE);
        this.g2d.setStroke(new BasicStroke(6));
        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                Cuadrante cuad = matriz.getNodo(i-1).getData().getNodo(j-1).getData();
                if (cuad.isOwned()) {
                    paintCuad(cuad, cuad.getOwner().getColor());
                }else {
                        if (cuad.getTop().isOwned()) {drawSide(cuad.getTop(),cuad.getTop().getOwner().getColor());}

                        if (cuad.getBot().isOwned()) {drawSide(cuad.getBot(),cuad.getBot().getOwner().getColor());}

                        if (cuad.getLeft().isOwned()) {drawSide(cuad.getLeft(),cuad.getLeft().getOwner().getColor());}

                        if (cuad.getRight().isOwned()){drawSide(cuad.getRight(),cuad.getRight().getOwner().getColor());}
                    }
                }
            }
        this.g2d.setPaint(Color.black);
        this.g2d.setStroke(new BasicStroke(1));
        this.g2d.drawRect(0,0,WIDTH,HEIGHT);
        for (int i=1; i<=10;i++){
            for (int j = 1; j <= 10; j++) {
                this.g2d.fillRect((80*j),(80*i),6,6);
            }
        }
        this.g2d.dispose();
    }
    public void mark(Cuadrante cuad,int side){
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
        paintComponent(this.getGraphics());
        update(this.getGraphics());


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Linea linea = marca.getData().getMarked();
        this.g2d =(Graphics2D) this.getGraphics();
        this.g2d.setColor(turno.getHighlight());
        this.g2d.setStroke(new BasicStroke(6));
        this.g2d.drawLine(linea.getX0(), linea.getY0(), linea.getX1(), linea.getY1());

    }
    public void drawSide(Linea linea, Color color) {
        this.g2d.setPaint(color);
        this.g2d.setStroke(new BasicStroke(6));
        this.g2d.drawLine(linea.getX0(), linea.getY0(), linea.getX1(), linea.getY1());
        }
    public void paintCuad(Cuadrante cuad, Color color){
        this.g2d.setPaint(color);
        this.g2d.fillRect(cuad.getTop().getX0(),cuad.getTop().getY0(),80,80);
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
                boolean gotPoint= false;
                Cuadrante cuad = marca.getData();
                int col = marca.getData().getCol();
                int row = marca.getData().getRow();
                // VECINOS
                Cuadrante cuadLeft = null;
                Cuadrante cuadUp = null;
                Cuadrante cuadDown = null;
                Cuadrante cuadRight = null;
                if (row>1){
                    cuadUp = marcaRow.getPrev().getData().getNodo(col - 1).getData();
                }
                if (row<9){
                    cuadDown = marcaRow.getNext().getData().getNodo(col - 1).getData();
                }
                if (col>1){
                    cuadLeft = marca.getPrev().getData();
                }
                if (col<9){
                    cuadRight = marca.getNext().getData();
                }
                switch (marcaPos) {
                    case 1 -> {
                        if (!cuad.getLeft().isOwned()) {
                            cuad.getLeft().setOwned(true);
                            cuad.getLeft().setOwner(turno);
                            cuad.getLeft().setMarked(false);
                            cuad.numSelec++;
                            if (cuadLeft != null) {
                                cuadLeft.getRight().setOwner(turno);
                                cuadLeft.getRight().setOwned(true);
                                cuadLeft.numSelec++;
                            }
                        }
                    }
                    case 2 -> {
                        if (!cuad.getTop().isOwned()) {
                            cuad.getTop().setOwned(true);
                            cuad.getTop().setOwner(turno);
                            cuad.getTop().setMarked(false);
                            cuad.numSelec++;
                            if (cuadUp != null) {
                                cuadUp.getBot().setOwner(turno);
                                cuadUp.getBot().setOwned(true);
                                cuadUp.numSelec++;
                            }
                        }
                    }
                    case 3 -> {
                        if (!cuad.getBot().isOwned()) {
                            cuad.getBot().setOwned(true);
                            cuad.getBot().setOwner(turno);
                            cuad.getBot().setMarked(false);
                            cuad.numSelec++;
                            if (cuadDown != null){
                                cuadDown.getTop().setOwner(turno);
                                cuadDown.getTop().setOwned(true);
                                cuadDown.numSelec++;
                            }

                        }
                    }
                    case 4 -> {
                        if (!cuad.getRight().isOwned()) {
                            cuad.getRight().setOwned(true);
                            cuad.getRight().setOwner(turno);
                            cuad.getRight().setMarked(false);
                            cuad.numSelec++;
                            if (cuadRight != null) {
                                cuadRight.getLeft().setOwner(turno);
                                cuadRight.getLeft().setOwned(true);
                                cuadRight.numSelec++;
                            }
                        }
                    }
                }

                System.out.println(cuad.getCoords() + cuad.getLeft().isOwned() + cuad.getTop().isOwned() + cuad.getBot().isOwned() + cuad.getRight().isOwned());

                if (cuad.numSelec==4 && !cuad.isOwned()){
                    cuad.getTop().setOwner(turno);
                    cuad.getLeft().setOwner(turno);
                    cuad.getBot().setOwner(turno);
                    cuad.getRight().setOwner(turno);
                    cuad.setOwned(true);
                    cuad.setOwner(turno);
                    gotPoint=true;
                    turno.addScore();
                    //System.out.println("Cuadrante " + cuad.getCoords() + " fue reclamado por " + turno.getNick());
                }
                if (cuadLeft != null) {
                    if (cuadLeft.numSelec == 4 && !cuadLeft.isOwned()) {
                        cuadLeft.getTop().setOwner(turno);
                        cuadLeft.getLeft().setOwner(turno);
                        cuadLeft.getBot().setOwner(turno);
                        cuadLeft.getRight().setOwner(turno);
                        cuadLeft.setOwned(true);
                        cuadLeft.setOwner(turno);
                        gotPoint=true;
                        turno.addScore();
                        //System.out.println("Cuadrante vecino izquierda" + cuadLeft.getCoords() + " fue reclamado por " + turno.getNick());
                    }
                }

                if (cuadUp != null) {
                    if (cuadUp.numSelec==4 && !cuadUp.isOwned()) {
                        cuadUp.getTop().setOwner(turno);
                        cuadUp.getLeft().setOwner(turno);
                        cuadUp.getBot().setOwner(turno);
                        cuadUp.getRight().setOwner(turno);
                        cuadUp.setOwned(true);
                        cuadUp.setOwner(turno);
                        gotPoint=true;
                        turno.addScore();
                        //System.out.println("Cuadrante vecino arriba" + cuadUp.getCoords() + " fue reclamado por " + turno.getNick());
                    }
                }

                if (cuadDown != null) {
                    if (cuadDown.numSelec==4 && !cuadDown.isOwned()) {
                        cuadDown.getTop().setOwner(turno);
                        cuadDown.getLeft().setOwner(turno);
                        cuadDown.getBot().setOwner(turno);
                        cuadDown.getRight().setOwner(turno);
                        cuadDown.setOwned(true);
                        cuadDown.setOwner(turno);
                        gotPoint=true;
                        turno.addScore();
                        //System.out.println("Cuadrante vecino abajo" + cuadDown.getCoords() + " fue reclamado por " + turno.getNick());
                    }
                }

                if (cuadRight != null) {
                    if (cuadRight.numSelec==4 && !cuadRight.isOwned()) {
                        cuadRight.getTop().setOwner(turno);
                        cuadRight.getLeft().setOwner(turno);
                        cuadRight.getBot().setOwner(turno);
                        cuadRight.getRight().setOwner(turno);
                        cuadRight.setOwned(true);
                        cuadRight.setOwner(turno);
                        gotPoint=true;
                        turno.addScore();
                        //System.out.println("Cuadrante vecino derecha " + cuadRight.getCoords() + " fue reclamado por " + turno.getNick());
                    }
                }
                if (!gotPoint){
                    cola.requeue();
                    turno=cola.getHead().getData();
                }
                System.out.println("UNO: " + p1.getScore());
                System.out.println("DOS: " + p2.getScore());
                System.out.println("TRES: " + p3.getScore());
            }
        }
        mark(marca.getData(), marcaPos);
    }
}
