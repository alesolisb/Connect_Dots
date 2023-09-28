import java.io.Serializable;

public class Mensaje implements Serializable {
    private Lista<Lista<Cuadrante>> matriz;
    public  Nodo<Cuadrante> marca;
    public  Nodo<Lista<Cuadrante>> marcaRow;
    public  int  marcaPos;
    private int keycode;
    private Player player;

    Mensaje(){
        this.matriz=null;
        this.keycode=0;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }
    Mensaje(int keycode){
        this.matriz=null;
        this.keycode=keycode;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }
    Mensaje(Lista<Lista<Cuadrante>> matriz){
        this.matriz=matriz;
        this.keycode=0;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }
    Mensaje(Lista<Lista<Cuadrante>> matriz,int keycode){
        this.matriz=matriz;
        this.keycode=keycode;
        this.player=null;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }
    Mensaje(Player player){
        this.matriz=null;
        this.keycode=0;
        this.player=player;
        this.marca=null;
        this.marcaRow=null;
        this.marcaPos=0;
    }
    Mensaje(Lista<Lista<Cuadrante>> matriz, Nodo<Cuadrante> marca, Nodo<Lista<Cuadrante>> marcaRow,int  marcaPos){
        this.matriz=matriz;
        this.keycode=0;
        this.player=null;
        this.marca=marca;
        this.marcaRow=marcaRow;
        this.marcaPos=marcaPos;
    }
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
