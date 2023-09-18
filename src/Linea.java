import java.awt.*;
import java.io.Serializable;
public class Linea implements Serializable{
    public Player owner;
    public boolean isOwned;
    public boolean isMarked;
    public int pos; // 1=left, 2=top, 3=bot, 4=right
    public int row;
    public int col;
    public int x0;
    public int y0;
    public int x1;
    public int y1;

    Linea(int pos, int row, int col){
        this.owner= null;
        this.isOwned= false;
        this.isMarked=false;
        this.pos=pos;
        this.row=row;
        this.col=col;
        switch (pos) {
            case 2 -> {
                this.x0 = (col * 80) + 3;
                this.y0 = (row * 80) + 3;
                this.x1 = this.x0 + 80;
                this.y1 = this.y0;
            }
            case 3 -> {
                this.x0 = (col * 80) + 3;
                this.y0 = (row * 80) + 80 + 3;
                this.x1 = this.x0 + 80;
                this.y1 = this.y0;
            }
            case 1 -> {
                this.x0 = (col * 80) + 3;
                this.y0 = (row * 80) + 3;
                this.x1 = this.x0;
                this.y1 = this.y0 + 80;
            }
            case 4 -> {
                this.x0 = (col * 80) + 80 + 3;
                this.y0 = (row * 80) + 3;
                this.x1 = this.x0;
                this.y1 = this.y0 + 80;
            }
        }
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

    public int getPos() {
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