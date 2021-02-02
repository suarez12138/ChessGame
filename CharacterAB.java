package JAVAhomework;

import java.awt.*;

public class CharacterAB {
    private int x, y;
    private Color currentcolor;
    private boolean eyeable = false;

    public CharacterAB clone() {
        return new CharacterAB(this.x,this.y,this.currentcolor,this.eyeable);
    }

    public CharacterAB(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public CharacterAB(int x, int y,Color currentcolor ,boolean eyeable) {
        this.x = x;
        this.y = y;
        this.currentcolor=currentcolor;
        this.eyeable=eyeable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCurrentcolor(Color currentcolor) { this.currentcolor = currentcolor; }

    public boolean isEyeable() {
        return eyeable;
    }

    public void setEyeable(boolean eyeable) {
        this.eyeable = eyeable;
    }

    public void paint() {
        Font f = new Font("Arial", Font.PLAIN, 120);
        StdDraw.setFont(f);
        if (!eyeable) {
            return;
        }
        if (currentcolor == Color.RED) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.text(x, y, "A");
        } else {
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.text(x, y, "B");
        }
    }
}