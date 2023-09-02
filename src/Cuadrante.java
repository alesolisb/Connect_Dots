public class Cuadrante extends Nodo<Cuadrante>{
    public int row;
    public int col;
    public Linea top;
    public Linea bot;
    public Linea left;
    public Linea right;
    public Player owner;
    public boolean isOwned;
     Cuadrante(int row, int col){
         super();
         this.next=null;
         this.prev=null;
         this.row = row;
         this.col = col;
         this.top = null; //new Linea('t');
         this.bot = null; //new Linea('b');
         this.left = null; //new Linea('l');
         this.right = null; //new Linea('r');
         this.owner = null;
         this.isOwned = false;
     }
     public String getCoords(){
         return "("+this.row+","+this.col+")";
     }

}
