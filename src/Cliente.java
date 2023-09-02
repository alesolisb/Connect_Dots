import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cliente extends JFrame implements MouseListener,Runnable {
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
}
