package JAVAhomework;

import java.awt.*;

public class Dot {
    private int x, y;
    private int size;

    public Dot(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void paint() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledCircle(x, y, size);
    }
}
