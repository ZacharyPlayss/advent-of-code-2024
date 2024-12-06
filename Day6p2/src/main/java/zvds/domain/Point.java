package zvds.domain;

public class Point {
    private int x;
    private int y;
    private boolean isRotationPoint;

    // Constructor for a Point with rotation information
    public Point(int x, int y, boolean isRotationPoint) {
        this.x = x;
        this.y = y;
        this.isRotationPoint = isRotationPoint;
    }

    // Getters and Setters
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

    public boolean isRotationPoint() {
        return isRotationPoint;
    }

    public void setRotationPoint(boolean isRotationPoint) {
        this.isRotationPoint = isRotationPoint;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", isRotationPoint=" + isRotationPoint +
                '}';
    }
}
