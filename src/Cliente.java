import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * Clase Cliente que conecta con el servidor y permite al usuario jugar la partida.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 */

public class Cliente extends JFrame implements KeyListener, Runnable {
    //Declaración de variables
    final int WIDTH = 900;
    final int HEIGHT = 1000;
    JPanel infoPanel;
    static Player player;
    C_Grid gridPanel;
    public String ip;
    /**
     * Constructor de la clase Cliente.
     */
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

/**
 * Clase C_Grid que representa el panel de juego y la cuadrícula.
 */
class C_Grid extends JPanel implements Serializable, Runnable{
    // Declaración de variables
    public Lista<Lista<Cuadrante>> matriz;
    public Nodo<Cuadrante> marca;
    public Nodo<Lista<Cuadrante>> marcaRow;
    public int marcaPos;
    public transient Graphics2D g2d;
    Thread hilo;
    Cola cola;
    Player turno;

    /**
     * Constructor de la clase C_Grid.
     * @param x Coordenada X del panel.
     * @param y Coordenada Y del panel.
     * @param width Ancho del panel.
     * @param height Alto del panel.
     */

    C_Grid(int x, int y, int width, int height){
        this.setLayout(new GridLayout(10,10));
        this.setBounds(x,y,width,height);
        this.setBackground(Color.white);
        hilo= new Thread(this);
        hilo.start();

    }

    /**
     * Método para pintar el componente.
     * @param g Objeto Graphics para dibujar.
     */

    @Override
    public void paint(Graphics g) {
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

    /**
     * Método para marcar un cuadrante y dibujar una línea.
     * @param cuad Cuadrante a marcar.
     * @param side Lado del cuadrante a marcar (1: izquierda, 2: arriba, 3: abajo, 4: derecha).
     */

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

    /**
     * Método para pintar un componente.
     * @param g Objeto Graphics para dibujar.
     */

    public void paintComponent(Graphics g){
            super.paintComponent(g);
            Linea linea = marca.getData().getMarked();
            this.g2d =(Graphics2D) this.getGraphics();
            this.g2d.setColor(Cliente.player.getHighlight());
            this.g2d.setStroke(new BasicStroke(6));
            this.g2d.drawLine(linea.getX0(), linea.getY0(), linea.getX1(), linea.getY1());
    }

    /**
     * Método para dibujar un lado de un cuadrante.
     * @param linea Objeto Linea que representa un lado del cuadrante.
     * @param color Color del lado.
     */

    public void drawSide(Linea linea, Color color) {
        this.g2d.setPaint(color);
        this.g2d.setStroke(new BasicStroke(6));
        this.g2d.drawLine(linea.getX0(), linea.getY0(), linea.getX1(), linea.getY1());
    }

    /**
     * Método para pintar un cuadrante.
     * @param cuad Cuadrante a pintar.
     * @param color Color del cuadrante.
     */

    public void paintCuad(Cuadrante cuad, Color color){
        this.g2d.setPaint(color);
        this.g2d.fillRect(cuad.getTop().getX0(),cuad.getTop().getY0(),80,80);
    }

    /**
     * Método principal para la ejecución del hilo.
     */

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

}
