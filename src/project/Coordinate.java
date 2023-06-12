package project;

import java.util.Random;

public class Coordinate {
    Random random = new Random();
    private Long id = random.nextLong(1000) + 1;
    private int coordinateX;
    private int coordinateY;
    private int coordinateZ;

    public Coordinate(int coordinateX, int coordinateY, int coordinateZ){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
    }

    public Coordinate() {
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getCoordinateZ() {
        return coordinateZ;
    }

    public void setCoordinateZ(int coordinateZ) {
        this.coordinateZ = coordinateZ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Coordinate createCoordinate(int width, int length, int height) {
        Random random = new Random();
        Coordinate coordinate =  new Coordinate();
        coordinate.setCoordinateX(random.nextInt(width) + 1);
        coordinate.setCoordinateY(random.nextInt(length) + 1);
        coordinate.setCoordinateZ(random.nextInt(height) + 1);
        return coordinate;
    }
}
