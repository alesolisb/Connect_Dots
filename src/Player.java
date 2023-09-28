import java.awt.Color;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Player implements Serializable {
    public Color color;

    public Color highlight;
    public String nick;

    public String ip;
    public int score;
    public Player(String nick,Color color, String ip){
        this.nick=nick;
        this.color=color;
        this.highlight=color.brighter();
        this.highlight=new Color(color.getRed(),color.getGreen(),color.getBlue(),100);
        this.score=0;
        this.ip=ip;
    }

    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public String getNick() {
        return this.nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public Color getHighlight() {
        return this.highlight;
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

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
