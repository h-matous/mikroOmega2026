package game;

public class Vector2i {
    private int x;
    private int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public Vector2i(int common) {
        setBoth(common);
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

    public void setBoth(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setBoth(int common) {
        this.x = common;
        this.y = common;
    }


    public void multiply(double factor) {
        this.x = (int) (this.x * factor);
        this.y = (int) (this.y * factor);
    }

    public void multiply(int factor) {
        this.x = this.x * factor;
        this.y = this.y * factor;
    }

    public void addX(int amount) {
        this.x = this.x + amount;
    }

    public void addY(int amount) {
        this.y = this.y + amount;
    }

    public void add(int amountX, int amountY) {
        addX(amountX);
        addY(amountY);
    }

    public void add(Vector2i amount) {
        add(amount.getX(), amount.getY());
    }
}
