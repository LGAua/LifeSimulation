package src;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates getRandomCoordinates(){
        int randomPositionX = (int) (Math.random() * WorldMap.getWorldSizeX());
        int randomPositionY = (int) (Math.random() * WorldMap.getWorldSizeY());
        return new Coordinates(randomPositionX, randomPositionY);
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
}
