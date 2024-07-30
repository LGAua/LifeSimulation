package src;

import java.util.Set;

public class Coordinates implements Comparable {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates getRandomCoordinates() {
        Set<Coordinates> occupiedPositions = WorldMap.getOccupiedPositions();
        Coordinates position;
        while (true) {
            int randomPositionX = (int) (Math.random() * WorldMap.getWorldSizeX());
            int randomPositionY = (int) (Math.random() * WorldMap.getWorldSizeY());
            position = new Coordinates(randomPositionX, randomPositionY);
            if (!occupiedPositions.contains(position)) {
                break;
            }
        }
        return position;
    }

    @Override
    public int compareTo(Object o) {
        Coordinates coordinates = (Coordinates) o;
        return x - coordinates.x;
    }

    @Override
    public int hashCode() {
        int result = 29;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates cord = (Coordinates) o;
        return x == cord.x && y == cord.y;
    }

    @Override
    public String toString() {
        String str = "x:" + this.x + " y:" + this.y;
        return str;
    }
}
