import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class Servidor implements Runnable{

    public static Lista<Lista<Cuadrante>> matriz;
    public static Nodo<Cuadrante> marca;
    public static Nodo<Lista<Cuadrante>> marcaRow;
    public static int  marcaPos;
    static Cola cola;


    public static void main (String[] args){

        Thread hilo = new Thread();
        hilo.start();

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

        cola = new Cola();

        MarcoServidor mimarco=new MarcoServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        try{
            ServerSocket matrizSSocket = new ServerSocket();
            Socket matrizS;
            ObjectInputStream matrizInput;
            while(true){
                matrizS = matrizSSocket.accept();
                matrizInput = new ObjectInputStream(matrizS.getInputStream());
                Servidor.matriz = (Lista<Lista<Cuadrante>>) matrizInput.readObject();

                Socket enviarMatriz = new Socket();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class MarcoServidor extends JFrame implements Runnable {




    public MarcoServidor(){



        final int WIDTH = 900;

        final int HEIGHT= 1000;

        Grid gridPanel;

        //JPanel infoPanel;

        this.setSize(WIDTH, HEIGHT);

        setBounds(0,0,1000,1000);


        //infoPanel=new JPanel(new FlowLayout());

        //infoPanel.setSize(new Dimension(WIDTH,HEIGHT-WIDTH));

//        infoPanel.setBounds(0,0,WIDTH,HEIGHT-WIDTH);

        //infoPanel.setBackground(Color.white);

        gridPanel= new Grid(0,HEIGHT-WIDTH,WIDTH,WIDTH);

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String gridJson = objectMapper.writeValueAsString(gridPanel);
//            //System.out.println(gridJson); // Solo para depuración, puedes enviarlo al cliente en su lugar
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //this.add(infoPanel);

        this.add(gridPanel);

        this.setVisible(true);

        JPanel milamina= new JPanel();

        milamina.setLayout(new BorderLayout());

        setVisible(true);

        Thread mihilo=new Thread(this);

        mihilo.start();

        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());

                // Envía el objeto Grid serializado al cliente
                objectOut.writeObject(Servidor.matriz);

                clientSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {

    }
}