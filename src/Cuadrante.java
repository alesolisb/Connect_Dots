/**
 * Clase Cuadrante que representa un cuadrante en el juego Connect Dots.
 * Extiende la clase Nodo para su gestión en listas enlazadas.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 */

public class Cuadrante extends Nodo<Cuadrante>{
    public int row;
    public int col;
    protected Cuadrante next;
    protected Cuadrante prev;
    public Linea top;
    public Linea bot;
    public Linea left;
    public Linea right;
    public Linea marked;
    public Player owner;
    public boolean isOwned;
    public int numSelec;

    /**
     * Constructor de la clase Cuadrante.
     * @param row Fila del cuadrante.
     * @param col Columna del cuadrante.
     */

    Cuadrante(int row, int col){
         this.next = null;
         this.prev = null;
         this.row = row;
         this.col = col;
         this.top = new Linea(2,this.row,this.col);
         this.bot = new Linea(3,this.row,this.col);
         this.left = new Linea(1,this.row,this.col);
         this.right = new Linea(4,this.row,this.col);
         this.owner = null;
         this.marked = null;
         this.isOwned = false;
         this.numSelec = 0;
    }
    public Linea getTop() {
        return this.top;
    }

    public Linea getBot() {
        return this.bot;
    }

    public Linea getLeft() {
        return this.left;
    }

    public Linea getRight() {
        return this.right;
    }

    public Linea getMarked() {
        return this.marked;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isOwned() {
        return this.isOwned;
    }

    public void setOwned(boolean owned) {
        this.isOwned = owned;
    }

    /**
     * Obtiene las coordenadas del cuadrante en formato "(col,row)".
     * @return Las coordenadas del cuadrante.
     */

    public String getCoords(){
         return "("+this.col+","+this.row+")";
     }

    /**
     * Establece el nodo siguiente al cuadrante en una lista enlazada.
     * @param nodo El cuadrante siguiente.
     */

    public void setNext(Cuadrante nodo) {this.next = nodo;}

    /**
     * Establece el nodo previo al cuadrante en una lista enlazada.
     * @param prev El cuadrante previo.
     */

    public void setPrev(Cuadrante prev) {this.prev = prev;}

    public int getNumSelec() {
        return numSelec;
    }

    public void setNumSelec(int numSelec) {
        this.numSelec = numSelec;
    }
}
