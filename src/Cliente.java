import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class Cliente extends JFrame implements KeyListener, Runnable {
    final int WIDTH = 900;
    final int HEIGHT = 1000;
    JPanel infoPanel;
    static Player player;
    C_Grid gridPanel;
    JLabel titulo, p1, p2, p3, p4;
    private JLabel mensajeLabel; // Nuevo JLabel para mostrar el mensaje de espera
    public Lista<Lista<Cuadrante>> matriz;
    public  Nodo<Cuadrante> marca;
    public  Nodo<Lista<Cuadrante>> marcaRow;
    public  int  marcaPos;

    public String ip;

    Cliente() {
        String nick= JOptionPane.showInputDialog("Introduce tu nombre de usuario: ");
        Color color = null;

        while (color==null || color==Color.WHITE){
            color = JColorChooser.showDialog(null,"Elige un color",Color.WHITE);
        }

        InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            ip = (localHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        player = new Player(nick,color,ip);

        try {
            Socket enviarOnline= new Socket("192.168.1.72",9999);
            Mensaje mensaje= new Mensaje(player);
            ObjectOutputStream envio = new ObjectOutputStream(enviarOnline.getOutputStream());
            envio.writeObject(mensaje);
            enviarOnline.close();
            envio.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Connect Dots");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.addKeyListener(this);


        infoPanel=new JPanel(new FlowLayout());
        //infoPanel.setSize(new Dimension(WIDTH,HEIGHT-WIDTH));
        infoPanel.setBounds(0,0,WIDTH,HEIGHT-WIDTH);
        infoPanel.setBackground(Color.black);

        gridPanel = new C_Grid(0,HEIGHT-WIDTH,WIDTH,WIDTH);
        this.add(infoPanel);
        this.add(gridPanel);
        //this.pack();
        this.setVisible(true);


/*
        p = new Player("Ale", Color.BLUE,Color.BLUE);

//        infoPanel = new JPanel(new FlowLayout());
//        infoPanel.setBounds(0, 0, WIDTH, HEIGHT - WIDTH);
//        infoPanel.setBackground(Color.black);

        // Crear y configurar el JLabel para mostrar el mensaje de espera
        mensajeLabel = new JLabel("Esperando conexión con el servidor");
        mensajeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mensajeLabel.setForeground(Color.RED);

        this.add(mensajeLabel);
        //this.add(gridPanel);

        // Agregar el JLabel al infoPanel
        //infoPanel.add(mensajeLabel);

        //this.add(infoPanel);
        this.setVisible(true);
*/
    }

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            Mensaje mensaje= new Mensaje(gridPanel.matriz,gridPanel.marca,gridPanel.marcaRow, gridPanel.marcaPos,e.getKeyCode());
            System.out.println("Enviado: " + mensaje.getKeycode());
            Socket enviarActualizacion = new Socket("192.168.1.72", 9999);
            ObjectOutputStream actOut = new ObjectOutputStream(enviarActualizacion.getOutputStream());
            actOut.writeObject(mensaje);

            enviarActualizacion.close();
            actOut.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
class C_Grid extends JPanel implements Serializable, Runnable{
    public Lista<Lista<Cuadrante>> matriz;
    public Nodo<Cuadrante> marca;
    public Nodo<Lista<Cuadrante>> marcaRow;
    public int marcaPos;
    public transient Graphics2D g2d;
    Thread hilo;
    Cola cola;
    Player turno;
    C_Grid(int x, int y, int width, int height){
        this.setLayout(new GridLayout(10,10));
        this.setBounds(x,y,width,height);
        this.setBackground(Color.white);
        hilo= new Thread(this);
        hilo.start();

    }
    @Override
    public void paint(Graphics g) {
        /*try {
            ServerSocket matrizSSocket = new ServerSocket(9999);
            Socket matrizS = matrizSSocket.accept();
            ObjectInputStream entrada = new ObjectInputStream(matrizS.getInputStream());

            Mensaje mensaje = (Mensaje) entrada.readObject();
            matriz = mensaje.getMatriz();

            matrizS.close();
            matrizSSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
//        System.out.println("Update");
        if (matriz != null) {
            this.g2d = (Graphics2D) g;
            this.g2d.setPaintMode();
            this.g2d.setBackground(Color.WHITE);
            this.g2d.setStroke(new BasicStroke(6));
            for (int i = 1; i < (matriz.getSize() + 1); i++) {
                for (int j = 1; j < 10; j++) {
                    Cuadrante cuad = matriz.getNodo(i - 1).getData().getNodo(j - 1).getData();
                    if (cuad.isOwned()) {
                        paintCuad(cuad, cuad.getOwner().getColor());
                    } else {
                        if (cuad.getTop().isOwned()) {
                            drawSide(cuad.getTop(), cuad.getTop().getOwner().getColor());
                        }

                        if (cuad.getBot().isOwned()) {
                            drawSide(cuad.getBot(), cuad.getBot().getOwner().getColor());
                        }

                        if (cuad.getLeft().isOwned()) {
                            drawSide(cuad.getLeft(), cuad.getLeft().getOwner().getColor());
                        }

                        if (cuad.getRight().isOwned()) {
                            drawSide(cuad.getRight(), cuad.getRight().getOwner().getColor());
                        }
                    }
                }
            }
            this.g2d.setPaint(Color.black);
            this.g2d.setStroke(new BasicStroke(1));
            this.g2d.drawRect(0, 0, WIDTH, HEIGHT);
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    this.g2d.fillRect((80 * j), (80 * i), 6, 6);
                }
            }
            this.g2d.dispose();
        }
    }
    public void mark(Cuadrante cuad,int side){
        for (int i=1; i<(matriz.getSize()+1);i++){
            for (int j = 1; j < 10; j++) {
                matriz.getNodo(i-1).getData().getNodo(j-1).getData().marked=null;
            }
        }
        System.out.println("SIDE: " + side);
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
            this.g2d.setColor(Cliente.player.getHighlight());
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

    @Override
    public void run() {
        try {
            ServerSocket actualizacion = new ServerSocket(9090);
            while (true){
                Socket act = actualizacion.accept();
                ObjectInputStream entrada = new ObjectInputStream(act.getInputStream());
                Mensaje mensaje = (Mensaje) entrada.readObject();
                matriz = mensaje.getMatriz();
                marca= mensaje.getMarca();
                marcaRow=mensaje.getMarcaRow();
                marcaPos=mensaje.getMarcaPos();
                this.mark(marca.getData(),marcaPos);
                act.close();
                entrada.close();
                Thread.sleep(100);
            }

        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
    /*public void navegar(int key) {
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
            }
        }
        mark(marca.getData(), marcaPos);
    }
*/
}
