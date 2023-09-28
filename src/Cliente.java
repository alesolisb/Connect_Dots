import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Cliente extends JFrame implements KeyListener, Runnable {
    final int WIDTH = 900;
    final int HEIGHT = 1000;
    //JPanel infoPanel;
    Player p;
    //Grid gridPanel;
    JLabel titulo, p1, p2, p3, p4;
    private JLabel mensajeLabel; // Nuevo JLabel para mostrar el mensaje de espera

    public Lista<Lista<Cuadrante>> matriz;


    Cliente() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Connect Dots");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.addKeyListener(this);
        Thread hilo = new Thread(this);
        hilo.start();

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

        try {
            hilo.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket actualizacion = new ServerSocket(9999);

            while (true){
                Socket socket = actualizacion.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                matriz = (Lista<Lista<Cuadrante>>) entrada.readObject();
                socket.close();
                entrada.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());

            // Recibe el objeto Grid serializado del servidor
            Grid gridPanel = (Grid) objectIn.readObject();

            socket.close();

            // Elimina el mensaje de espera cuando se establezca la conexión
            this.remove(mensajeLabel);
            this.add(gridPanel); // Agrega el objeto Grid al cliente
            this.repaint();

            gridPanel.navegar(e.getKeyCode());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
