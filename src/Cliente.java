import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends JFrame implements MouseListener, KeyListener,Runnable {
     final int WIDTH = 900;
     final int HEIGHT= 1000;
     JPanel infoPanel;
     Player p;
     Grid gridPanel;
     JLabel titulo,p1,p2,p3,p4;

     Cliente(){
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setTitle("Connect Dots");
         this.setLayout(null);
         this.setResizable(false);
         this.setSize(WIDTH, HEIGHT);
         this.addKeyListener(this);


         p=new Player("Ale",Color.BLUE);


         infoPanel=new JPanel(new FlowLayout());
         //infoPanel.setSize(new Dimension(WIDTH,HEIGHT-WIDTH));
         infoPanel.setBounds(0,0,WIDTH,HEIGHT-WIDTH);
         infoPanel.setBackground(Color.black);

         gridPanel= new Grid(0,HEIGHT-WIDTH,WIDTH,WIDTH);
         this.add(infoPanel);
         this.add(gridPanel);
         //this.pack();
         this.setVisible(true);


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

    @Override
    public void run() {

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
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        try {
            Socket socket = new Socket("127.0.0.1", 12345); // Cambia el puerto si es necesario
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            int keyCode = e.getKeyCode();
            KeyEventMessage keyEventMessage = new KeyEventMessage(keyCode);

            // Enviar el evento al servidor en formato JSON
            out.println(keyEventMessage.toJson());

            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //System.out.println(e.getKeyCode());
        gridPanel.navegar(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
