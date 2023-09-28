import javax.swing.*;
import java.awt.*;

/**
 * Clase principal Main que inicia el servidor y el cliente del juego Connect Dots.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 */

public class Main {
    public static void main(String[] args){
        // Inicia el servidor y el cliente al ejecutar la aplicación.
//        new Servidor();
//        new Cliente();
        String[] resp = {"Cliente","Servidor"};

        int ans = JOptionPane.showOptionDialog(null,
                "¿Desea abrir un cliente o un servidor?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                resp,
                0);
        System.out.println(ans);
        if (ans==0){new Cliente();}
        else if (ans==1){new Servidor();}
    }
}
