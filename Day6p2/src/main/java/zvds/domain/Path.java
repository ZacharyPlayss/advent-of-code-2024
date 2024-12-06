package zvds.domain;

import zvds.domain.Point;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Point> pathPoints = new ArrayList<>();

    public void addPoint(Point point) {
        pathPoints.add(point);
    }

    public boolean isRotationPoint(int x, int y) {
        for (Point point : pathPoints) {
            if (point.getX() == x && point.getY() == y) {
                return point.isRotationPoint();
            }
        }
        return false;
    }

    public void setRotationPoint(int x, int y, boolean isRotationPoint) {
        for (Point point : pathPoints) {
            if (point.getX() == x && point.getY() == y) {
                point.setRotationPoint(isRotationPoint);
            }
        }
    }

    public void printPath() {
        for (Point point : pathPoints) {
            System.out.println(point);
        }
    }

    public boolean containsPoint(int currentGuardX, int currentGuardY) {
        for (Point point : pathPoints) {
            if (point.getX() == currentGuardX && point.getY() == currentGuardY) {
                return true;
            }
        }
        return false;
    }
}
