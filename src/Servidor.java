import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Servidor {
    public static void main (String[] args){
        MarcoServidor mimarco=new MarcoServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoServidor extends JFrame implements MouseListener, KeyListener, Runnable {

    public MarcoServidor(){

        final int WIDTH = 900;

        final int HEIGHT= 1000;

        Grid gridPanel;

        JPanel infoPanel;

        setBounds(0,0,1000,1000);

        infoPanel=new JPanel(new FlowLayout());

        //infoPanel.setSize(new Dimension(WIDTH,HEIGHT-WIDTH));

        infoPanel.setBounds(0,0,WIDTH,HEIGHT-WIDTH);

        infoPanel.setBackground(Color.black);

        gridPanel= new Grid(0,HEIGHT-WIDTH,WIDTH,WIDTH);

        this.add(infoPanel);

        this.add(gridPanel);

        this.setVisible(true);

        JPanel milamina= new JPanel();

        milamina.setLayout(new BorderLayout());

        setVisible(true);

        Thread mihilo=new Thread(this);

        mihilo.start();

        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Cambia el puerto si es necesario

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Recibir el evento JSON del cliente
                String jsonEvent = in.readLine();
                KeyEventMessage keyEventMessage = KeyEventMessage.fromJson(jsonEvent);

                // Procesar el evento (ejemplo: llamar a gridPanel.navegar)
                gridPanel.navegar(keyEventMessage.getKeyCode());

                clientSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        //gridPanel.navegar(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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