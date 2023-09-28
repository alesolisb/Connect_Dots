import java.io.Serializable;

/**
 * Clase Mensaje que representa un mensaje serializable en el juego Connect Dots.
 * Contiene información relevante para la comunicación entre el cliente y el servidor.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 */

public class Mensaje implements Serializable {
    private Lista<Lista<Cuadrante>> matriz;
    public  Nodo<Cuadrante> marca;
    public  Nodo<Lista<Cuadrante>> marcaRow;
    public  int  marcaPos;
    private int keycode;
    private Player player;

    /**
     * Constructor de la clase Mensaje sin argumentos.
     * Inicializa los campos con valores predeterminados.
     */

    Mensaje(){
        this.matriz=null;
        this.keycode=0;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }

    /**
     * Constructor de la clase Mensaje con un código de tecla.
     * @param keycode El código de tecla asociado al mensaje.
     */

    Mensaje(int keycode){
        this.matriz=null;
        this.keycode=keycode;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }

    /**
     * Constructor de la clase Mensaje con una matriz de cuadrantes.
     * @param matriz La matriz de cuadrantes asociada al mensaje.
     */

    Mensaje(Lista<Lista<Cuadrante>> matriz){
        this.matriz=matriz;
        this.keycode=0;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }

    /**
     * Constructor de la clase Mensaje que inicializa la matriz de cuadrantes y el código de tecla.
     * @param matriz La matriz de cuadrantes asociada al mensaje.
     * @param keycode El código de tecla asociado al mensaje.
     */

    Mensaje(Lista<Lista<Cuadrante>> matriz,int keycode){
        this.matriz=matriz;
        this.keycode=keycode;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }

    /**
     * Constructor de la clase Mensaje que inicializa el jugador.
     * @param player El jugador asociado al mensaje.
     */

    Mensaje(Player player){
        this.matriz=null;
        this.keycode=0;
        this.player=player;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }

    /**
     * Constructor de la clase Mensaje que inicializa la matriz de cuadrantes, la marca, la fila de la marca
     * y la posición de la marca.
     * @param matriz La matriz de cuadrantes asociada al mensaje.
     * @param marca El nodo de cuadrante marcado asociado al mensaje.
     * @param marcaRow El nodo de fila de cuadrantes marcados asociado al mensaje.
     * @param marcaPos La posición de la marca asociada al mensaje.
     */

    Mensaje(Lista<Lista<Cuadrante>> matriz, Nodo<Cuadrante> marca, Nodo<Lista<Cuadrante>> marcaRow,int  marcaPos){
        this.matriz=matriz;
        this.keycode=0;
        this.player=null;
        this.marca=marca;
        this.marcaRow=marcaRow;
        this.marcaPos=marcaPos;
    }

    /**
     * Constructor de la clase Mensaje que inicializa la matriz de cuadrantes, la marca, la fila de la marca,
     * la posición de la marca y el código de tecla.
     * @param matriz La matriz de cuadrantes asociada al mensaje.
     * @param marca El nodo de cuadrante marcado asociado al mensaje.
     * @param marcaRow El nodo de fila de cuadrantes marcados asociado al mensaje.
     * @param marcaPos La posición de la marca asociada al mensaje.
     * @param keycode El código de tecla asociado al mensaje.
     */

    Mensaje(Lista<Lista<Cuadrante>> matriz, Nodo<Cuadrante> marca, Nodo<Lista<Cuadrante>> marcaRow,int  marcaPos, int keycode){
        this.matriz=matriz;
        this.keycode=keycode;
        this.player=null;
        this.marca=marca;
        this.marcaRow=marcaRow;
        this.marcaPos=marcaPos;
    }
    public Lista<Lista<Cuadrante>> getMatriz() {
        return matriz;
    }

    public void setMatriz(Lista<Lista<Cuadrante>> matriz) {
        this.matriz = matriz;
    }

    public int getKeycode() {
        return keycode;
    }

    public void setKeycode(int keycode) {
        this.keycode = keycode;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Nodo<Cuadrante> getMarca() {
        return marca;
    }

    public void setMarca(Nodo<Cuadrante> marca) {
        this.marca = marca;
    }

    public Nodo<Lista<Cuadrante>> getMarcaRow() {
        return marcaRow;
    }

    public void setMarcaRow(Nodo<Lista<Cuadrante>> marcaRow) {
        this.marcaRow = marcaRow;
    }

    public int getMarcaPos() {
        return marcaPos;
    }

    public void setMarcaPos(int marcaPos) {
        this.marcaPos = marcaPos;
    }
}
