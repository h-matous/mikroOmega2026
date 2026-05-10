package game;

public class Vector2d {
    private double x;
    private double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public Vector2d(double common) {
        setBoth(common);
    }

    public Vector2d() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setBoth(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setBoth(double common) {
        this.x = common;
        this.y = common;
    }

    public void multiply(double factor) {
        this.x = this.x * factor;
        this.y = this.y * factor;
    }

    public void multiply(int factor) {
        this.x = this.x * factor;
        this.y = this.y * factor;
    }

    public void addX(double amount) {
        this.x = this.x + amount;
    }

    public void addY(double amount) {
        this.y = this.y + amount;
    }

    public void add(double amountX, double amountY) {
        addX(amountX);
        addY(amountY);
    }

    public void add(Vector2d amount) {
        add(amount.getX(), amount.getY());
    }
}
