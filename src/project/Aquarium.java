package project;

import project.enums.Gender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static project.Start.aquarium;
import static project.Start.checkFull;
import static project.enums.Gender.FEMALE;

public class Aquarium {
    private static final Random random = ThreadLocalRandom.current();
    private Long id = random.nextLong(1000) + 1;
    private int width;
    private int length;
    private int height;
    private LinkedList<Fish> fishList;


    public Aquarium() {
        fishList = new LinkedList<>();
    }

    public Aquarium(int weight, int length, int height) {
        this.width = weight;
        this.length = length;
        this.height = height;
        fishList = new LinkedList<>();
    }


    public void simulate() {

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (fishList.size() == 0) {
                        System.out.println("Aquarium is empty");
                        break;
                    }
                    for (int i = 0; i < fishList.size(); i++) {
                        Fish fish = fishList.get(i);
                        if (fish.isAlive()) {
                            System.out.println(i + 1 + " ID: " + fish.getId() + " Thread Fish is swimming gender: " + fish.getGender() +
                                    ", lifespan: " + fish.getLifespan() +
                                    ", position: [" + fish.getPosition().getX() + ", " +
                                    fish.getPosition().getY() + ", " +
                                    fish.getPosition().getZ() + "]," +
                                    "Parent: " + fish.getParent());
                            if (fish.getLifespan() < 1) {
                                System.out.println("This fish is died: ID " + fish.getId());
                                fishList.remove(i);
                            }
                            fish.setLifespan(fish.getLifespan() - 1);
                        }
                    }
                    checkCollision(fishList);
                    for (Fish fish : fishList) {
                        Coordinate coordinate = Coordinate.createCoordinate(width, length, height);
                        fish.setPosition(coordinate);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("=================================================================================");
            }
        });
        thread.start();
    }

    private void checkCollision(LinkedList<Fish> fishList) {
        for (int i = 0; i < fishList.size() - 1; i++) {
            Fish fishA = fishList.get(i);
            fishList.set(i, fishA);
            int xA = fishA.getPosition().getX();
            int yA = fishA.getPosition().getY();
            int zA = fishA.getPosition().getZ();
            for (int j = i + 1; j < fishList.size() - 1; j++) {
                Fish fishB = fishList.get(j);
                int xB = fishB.getPosition().getX();
                int yB = fishB.getPosition().getY();
                int zB = fishB.getPosition().getZ();

                long fatherId = fishA.getGender().equals(Gender.MALE) ? fishA.getId() : fishB.getId();
                long motherId = fishA.getGender().equals(FEMALE) ? fishB.getId() : fishA.getId();
                if (checkCoordinate(xA, xB, yA, yB, zA, zB) &&
                        !fishA.getGender().equals(fishB.getGender()) &&
                        fishB.getFatherId() == fatherId &&
                        fishB.getMotherId() == motherId){
                    System.out.println("This fish is parent: ---------------------------------");

                }
                if (checkCoordinate(xA, xB, yA, yB, zA, zB) &&
                        !fishA.getGender().equals(fishB.getGender()) &&
                        fishA.getFatherId() != fatherId &&
                        fishA.getMotherId() != motherId &&
                        fishB.getFatherId() != fatherId &&
                        fishB.getMotherId() != motherId) {
                    Gender gender = random.nextBoolean() ? Gender.MALE : FEMALE;
                    Coordinate coordinate = Coordinate.createCoordinate(aquarium.getWidth(), aquarium.getLength(), aquarium.getHeight());

                    String parent = "Father: " + fishA.getId() + ", Mother " + fishB.getId();
                    Fish fish = new Fish(gender, coordinate, parent, fatherId, motherId);
                    if (!checkFull()) {
                        fishList.add(fish);
                        System.out.println("The fish met at the coordinate: ["
                                + fishA.getPosition().getX() + ", "
                                + fishA.getPosition().getY() + ", "
                                + fishA.getPosition().getZ() + "] " +
                                "This fish ID: " + fish.getId() +
                                " This fish is gender:"
                                + fish.getGender() + ", lifespan: "
                                + fish.getLifespan() + ", This fish parent: "
                                + fish.getParent());
                    break;
                    }
                    fishList.remove(fish);
                    System.out.println("Aquarium is full");
                }
            }


        }
    }

    private boolean checkGender(Gender male, Gender female) {
        return male.equals(female);
    }

    private boolean checkCoordinate(int xA, int xB, int yA, int yB, int zA, int zB) {
        return xA == xB && yA == yB && zA == zB;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addFish(Fish fish) {
        fishList.add(fish);
    }

    public List<Fish> getFishList() {
        return fishList;
    }

    public void setFishList(LinkedList<Fish> fishList) {
        this.fishList = fishList;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}


