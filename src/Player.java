import java.awt.Color;

public class Player {
    public Color color;
    public String nick;
    public int score;
    public Player(String nick,Color color){
        this.nick=nick;
        this.color=color;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        score = score;
    }
    public void addScore(int x){
        score=score+x;
    }
}
