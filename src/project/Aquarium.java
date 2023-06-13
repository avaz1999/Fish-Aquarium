package project;

import com.sun.tools.javac.Main;
import project.enums.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static project.Start.aquarium;
import static project.enums.Gender.FEMALE;

public class Aquarium {
    private static final Random random = ThreadLocalRandom.current();
    private Long id = random.nextLong(1000) + 1;
    private int width;
    private int length;
    private int height;
    private List<Fish> fishList;


    public Aquarium() {
        fishList = new ArrayList<>();
    }

    public Aquarium(int weight, int length, int height) {
        this.width = weight;
        this.length = length;
        this.height = height;
        fishList = new ArrayList<>();
    }


    public void simulate() {
        Fish fishA = new Fish(Gender.MALE, new Coordinate(1, 3, 3), "", 0, 0);
        Fish fishB = new Fish(FEMALE, new Coordinate(1, 3, 3), "", 0, 0);
        fishList.add(fishA);
        fishList.add(fishB);
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
                                    ", position: [" + fish.getPosition().getCoordinateX() + ", " +
                                    fish.getPosition().getCoordinateY() + ", " +
                                    fish.getPosition().getCoordinateZ() + "]," +
                                    "Parent: " + fish.getParent());
                            fish.setLifespan(fish.getLifespan() - 1);
                            if (fish.getLifespan() < 1) {
                                System.out.println("This fish is died: ID " + fish.getId());
                                fishList.remove(i);
                            }
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
            }
        });
        thread.start();
    }

    private void checkCollision(List<Fish> fishList) {
        for (int i = 0; i < fishList.size() - 1; i++) {
            Fish fishA = fishList.get(i);
            Fish fishB = fishList.get(i + 1);
            long fatherId = fishA.getGender().equals(Gender.MALE) ? fishA.getId() : fishB.getId();
            long motherId = fishA.getGender().equals(FEMALE) ? fishB.getId() : fishA.getId();
            int xA = fishA.getPosition().getCoordinateX();
            int xB = fishB.getPosition().getCoordinateX();
            int yA = fishA.getPosition().getCoordinateY();
            int yB = fishB.getPosition().getCoordinateY();
            int zA = fishA.getPosition().getCoordinateZ();
            int zB = fishB.getPosition().getCoordinateZ();

            if (checkCoordinate(xA,xB,yA,yB,zA,zB) &&
                    !fishA.getGender().equals(fishB.getGender()) &&
                    fishA.getFatherId() != fatherId &&
                    fishA.getMotherId() != motherId &&
                    fishB.getFatherId() != fatherId &&
                    fishB.getMotherId() != motherId) {
                Gender gender = random.nextBoolean() ? Gender.MALE : FEMALE;
                Coordinate coordinate = Coordinate.createCoordinate(aquarium.getWidth(), aquarium.getLength(), aquarium.getHeight());

                String parent = "Father: " + fishA.getId() + ", Mother " + fishB.getId();
                Fish fish = new Fish(gender, coordinate, parent, fatherId, motherId);
                fishList.add(fish);
                System.out.println("The fish met at the coordinate: ["
                        + fishA.getPosition().getCoordinateX() + ", "
                        + fishA.getPosition().getCoordinateY() + ", "
                        + fishA.getPosition().getCoordinateZ() + "] " +
                        "This fish ID: " + fish.getId() +
                        " This fish is gender:"
                        + fish.getGender() + ", lifespan: "
                        + fish.getLifespan() + ", This fish parent: "
                        + fish.getParent());
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

    public void setFishList(List<Fish> fishList) {
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


