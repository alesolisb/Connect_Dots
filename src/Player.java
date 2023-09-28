import java.awt.Color;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Clase Player que representa un jugador en el juego.
 * Cada jugador tiene un nombre de usuario, un color de juego, un resaltado de color,
 * un puntaje, y una dirección IP asociada.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 */

public class Player implements Serializable {
    public Color color;
    public Color highlight;
    public String nick;
    public String ip;
    public int score;

    /**
     * Constructor de la clase Player.
     * @param nick El nombre de usuario del jugador.
     * @param color El color del jugador en el juego.
     * @param ip La dirección IP asociada al jugador.
     */

    public Player(String nick,Color color, String ip){
        this.nick=nick;
        this.color=color;
        this.highlight=color.brighter();
        this.highlight=new Color(color.getRed(),color.getGreen(),color.getBlue(),100);
        this.score=0;
        this.ip=ip;
    }
    /**
     * Obtiene el color del jugador en el juego.
     * @return El color del jugador.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Establece el color del jugador en el juego.
     * @param color El nuevo color del jugador.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Obtiene el nombre de usuario del jugador.
     * @return El nombre de usuario del jugador.
     */
    public String getNick() {
        return this.nick;
    }
    /**
     * Establece el nombre de usuario del jugador.
     * @param nick El nuevo nombre de usuario del jugador.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }
    /**
     * Obtiene el color de resaltado del jugador.
     * @return El color de resaltado del jugador.
     */
    public Color getHighlight() {
        return this.highlight;
    }
    /**
     * Establece el color de resaltado del jugador.
     * @param highlight El nuevo color de resaltado del jugador.
     */
    public void setHighlight(Color highlight) {
        this.highlight = highlight;
    }
    /**
     * Obtiene el puntaje actual del jugador.
     * @return El puntaje del jugador.
     */
    public int getScore() {
        return this.score;
    }
    /**
     * Establece el puntaje del jugador.
     * @param score El nuevo puntaje del jugador.
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * Incrementa el puntaje del jugador en 1.
     */
    public void addScore(){
        this.score=this.score+1;
    }
    /**
     * Obtiene la dirección IP asociada al jugador.
     * @return La dirección IP del jugador.
     */
    public String getIp() {
        return this.ip;
    }
    /**
     * Establece la dirección IP asociada al jugador.
     * @param ip La nueva dirección IP del jugador.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}
