import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    Grid(int x, int y, int width, int height){
        this.setLayout(new GridLayout(10,10));
        this.setBounds(x,y,width,height);
        this.setBackground(Color.white);


    }
    @Override
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1));
        for (int i=1; i<=10;i++){
            for (int j = 1; j <= 10; j++) {
                g2d.fillRect((80*j),(80*i),6,6);
            }
        }

    }
}
