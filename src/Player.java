import java.awt.Color;

public class Player {
    public Color color;
    public String nick;

    public Player(String nick,Color color){
        this.nick=nick;
        this.color=color;
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
}
