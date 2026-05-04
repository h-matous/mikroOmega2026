package game;

public class Vector2d {
    private double x;
    private double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
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

    public void offsetX(double amount) {
        x = x + amount;
    }

    public void offsetY(double amount) {
        y = y + amount;
    }

    public void offset(double amountX, double amountY) {
        offsetX(amountX);
        offsetY(amountY);
    }

    public void offset(Vector2d amount) {
        offset(amount.getX(), amount.getY());
    }
}
