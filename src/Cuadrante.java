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
         this.top = new Linea('t',this.row,this.col);
         this.bot =  new Linea('b',this.row,this.col);
         this.left =  new Linea('l',this.row,this.col);
         this.right =  new Linea('r',this.row,this.col);
         this.owner = null;
         this.isOwned = false;
     }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public String getCoords(){
         return "("+this.row+","+this.col+")";
     }

}
