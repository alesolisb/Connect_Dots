public class Linea {
    public Player owner;
    public boolean isOwned;
    public boolean isMarked;
    public char pos; // 't', 'b', 'l', 'r'
    public int x0;
    public int y0;
    public int x1;
    public int y1;

    Linea(char pos, int x0, int y0, int x1, int y1){
        this.owner= null;
        this.isOwned= false;
        this.isMarked=false;
        this.pos=pos;
    }

    public Player getOwner(){return this.owner;}
    public void setMarked(boolean marked){this.isMarked = marked;}

    public void setOwner(Player owner) {
        this.owner = owner;
        this.isOwned=true;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public char getPos() {
        return pos;
    }

    public void setPos(char pos) {
        this.pos = pos;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }
}