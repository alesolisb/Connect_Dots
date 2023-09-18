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
    private Grid gridPanel;
    JLabel titulo, p1, p2, p3, p4;
    private JLabel mensajeLabel; // Nuevo JLabel para mostrar el mensaje de espera


    Cliente() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Connect Dots");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.addKeyListener(this);

        p = new Player("Ale", Color.BLUE);

//        infoPanel = new JPanel(new FlowLayout());
//        infoPanel.setBounds(0, 0, WIDTH, HEIGHT - WIDTH);
//        infoPanel.setBackground(Color.black);

        // Crear y configurar el JLabel para mostrar el mensaje de espera
        mensajeLabel = new JLabel("Esperando conexión con el servidor");
        mensajeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mensajeLabel.setForeground(Color.RED);

        this.add(mensajeLabel);

        // Agregar el JLabel al infoPanel
        //infoPanel.add(mensajeLabel);

        //this.add(infoPanel);
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
            Socket socket = new Socket("127.0.0.1", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());

            // Recibir la instancia de Grid del servidor
            gridPanel = (Grid) objectIn.readObject();

            int keyCode = e.getKeyCode();
            KeyEventMessage keyEventMessage = new KeyEventMessage(keyCode);

            // Enviar el evento al servidor en formato JSON
            out.println(keyEventMessage.toJson());

            socket.close();

            // Eliminar el mensaje de espera cuando se establezca la conexión
//            infoPanel.remove(mensajeLabel);
//            infoPanel.revalidate();
//            infoPanel.repaint();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        this.add(gridPanel);
        gridPanel.repaint();

        gridPanel.navegar(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
