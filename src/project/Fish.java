package project;

import project.enums.Gender;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Fish {
    private static final Random random = ThreadLocalRandom.current();
    private Long id = random.nextLong(10000);
    private Gender gender;
    private long lifespan;
    private long birthTime;
    private Coordinate position;
    private boolean alive;
    private long size;

    public Fish(Gender gender, Coordinate position,Long size) {
        this.position = position;
        this.gender = gender;
        this.lifespan = random.nextInt(10) + 1;
        this.size = size;
        birthTime = System.currentTimeMillis();
        alive = true;
    }

    public void swim() {
        System.out.println("Thread Fish is swimming gender: " + gender +
                ", lifespan: " + lifespan +
                ", position: [" + position.getCoordinateX() + ", " +
                position.getCoordinateY() + ", " +
                position.getCoordinateZ() + "]");
    }

    public Fish() {
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getLifespan() {
        return lifespan;
    }

    public void setLifespan(long lifespan) {
        this.lifespan = lifespan;
    }

    public long getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(long birthTime) {
        this.birthTime = birthTime;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
