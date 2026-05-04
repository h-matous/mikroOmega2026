package game;

public class Vector2i {
    private int x;
    private int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void offsetX(int amount) {
        x = x + amount;
    }

    public void offsetY(int amount) {
        y = y + amount;
    }

    public void offset(int amountX, int amountY) {
        offsetX(amountX);
        offsetY(amountY);
    }

    public void offset(Vector2i amount) {
        offset(amount.getX(), amount.getY());
    }
}
