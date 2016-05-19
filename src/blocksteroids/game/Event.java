package blocksteroids.game;

public class Event {
    
    public int type;
    public int code;
    public int x;
    public int y;

    public Event(int type, int x, int y, int code) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.code = code;
    }
}
