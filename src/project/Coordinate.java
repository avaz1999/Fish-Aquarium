package project;


import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

import static project.Start.aquarium;

public class Coordinate {
    Random random = new Random();
    private Long id = random.nextLong(1000) + 1;
    private int x;
    private int y;
    private int z;

    public Coordinate(int coordinateX, int coordinateY, int coordinateZ) {
        this.x = coordinateX;
        this.y = coordinateY;
        this.z = coordinateZ;
    }

    public Coordinate() {
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Coordinate createCoordinate(int width, int length, int height) {
        Random random = new Random();
        Coordinate coordinate = new Coordinate();
        coordinate.setX(random.nextInt(width) + 1);
        coordinate.setY(random.nextInt(length) + 1);
        coordinate.setZ(random.nextInt(height) + 1);
        return coordinate;
    }



    private static Fish moveBackward(Fish fish) {
        Coordinate coordinate = new Coordinate(fish.getPosition().x, fish.getPosition().y, fish.getPosition().z--);
        fish.setPosition(coordinate);
        return fish;
    }

    private static Fish moveForward(Fish fish) {
        Coordinate coordinate = new Coordinate(fish.getPosition().x, fish.getPosition().y, fish.getPosition().z++);
        fish.setPosition(coordinate);
        return fish;
    }

    private static Fish moveDown(Fish fish) {
        Coordinate coordinate = new Coordinate(fish.getPosition().x, fish.getPosition().y--, fish.getPosition().z);
        fish.setPosition(coordinate);
        return fish;
    }

    private static Fish moveUp(Fish fish) {
        Coordinate coordinate = new Coordinate(fish.getPosition().x, fish.getPosition().y++, fish.getPosition().z);
        fish.setPosition(coordinate);
        return fish;
    }

    private static Fish moveLeft(Fish fish) {
        Coordinate coordinate = new Coordinate(fish.getPosition().x--, fish.getPosition().y, fish.getPosition().z);
        fish.setPosition(coordinate);
        return fish;
    }

    private static Fish moveRight(Fish fish) {
        Coordinate coordinate = new Coordinate(fish.getPosition().x++, fish.getPosition().y, fish.getPosition().z);
        fish.setPosition(coordinate);
        return fish;
    }
}
