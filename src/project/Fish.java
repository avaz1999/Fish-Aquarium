package project;

import project.enums.Gender;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Fish {
    private static final Random random = ThreadLocalRandom.current();
    private Long id = random.nextLong(10000);
    private long fatherId = 0;
    private long motherId = 0;
    private Gender gender;
    private long lifespan;
    private long birthTime;
    private Coordinate position;
    private boolean alive;
    private long size = 2L;
    private String parent = " ";

    public Fish(Gender gender, Coordinate position,String parent,long fatherId,long motherId) {
        this.position = position;
        this.gender = gender;
        this.parent = parent;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.lifespan = random.nextInt(10) + 1;
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

    public void setSize(long size) {
        this.size = size;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public long getMotherId() {
        return motherId;
    }

    public void setMotherId(long motherId) {
        this.motherId = motherId;
    }
}
