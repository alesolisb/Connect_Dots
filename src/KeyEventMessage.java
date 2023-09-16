import com.google.gson.Gson;

public class KeyEventMessage {
    private int keyCode;

    public KeyEventMessage(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    // Método para convertir el objeto a JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Método estático para convertir JSON a objeto KeyEventMessage
    public static KeyEventMessage fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, KeyEventMessage.class);
    }
}