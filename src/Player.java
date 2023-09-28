import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable {
    public Color color;

    public Color highlight;
    public String nick;
    public int score;
    public Player(String nick,Color color,Color highlight){
        this.nick=nick;
        this.color=color;
        this.highlight=highlight;
        this.score=0;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public Color getHighlight() {
        return highlight;
    }
    public void setHighlight(Color highlight) {
        this.highlight = highlight;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void addScore(){
        this.score=this.score+1;
    }
}
