/**
 * Clase Cola que representa una cola de jugadores.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 */
public class Cola extends Lista<Player>{

    /**
     * Método para encolar un jugador al final de la cola.
     * @param x El jugador a encolar.
     */

    public void enqueue(Player x){insertLast(x);}

    /**
     * Método para desencolar un jugador de la cabeza de la cola.
     * @return El jugador desencolado.
     */

    public Player dequeue(){return deleteFirst().getData();}

    /**
     * Método para obtener al jugador en la cabeza de la cola sin desencolarlo.
     * @return El jugador en la cabeza de la cola.
     */

    public Player peek(){return getHead().getData();}

    /**
     * Método para recolocar el jugador en la cola (sacarlo de la cabeza y volverlo a encolar al final).
     */
    public void requeue(){
        enqueue(dequeue());
    }
}