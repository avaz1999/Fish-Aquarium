package project;

import project.enums.Gender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static project.Fish.swimming;
import static project.Start.aquarium;
import static project.enums.Gender.FEMALE;
import static project.enums.Gender.MALE;

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

//        Fish fish2 = new Fish(MALE, new Coordinate(1, 1, 1), "", 0, 0);
//        Fish fish3 = new Fish(FEMALE, new Coordinate(1, 1, 1), "", 0, 0);
//        String parent = "Parent: " + "FatherId: " + fish2.getId() + " MotherId: " + fish3.getId();
//        Fish fish1 = new Fish(FEMALE, new Coordinate(fish2.getPosition().getX(), fish2.getPosition().getY(), fish2.getPosition().getZ()), parent, fish2.getId(), fish3.getMotherId());
//        fishList.add(fish2);
//        fishList.add(fish3);
//        fishList.add(fish1);
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (checkAquariumEmpty()) {
                        System.out.println("Aquarium is empty");
                        break;
                    }
                    swimming();

                    checkCollision(fishList);

                    for (Fish fish : fishList) {
                        Coordinate coordinate = Coordinate.createCoordinate(aquarium.width, aquarium.length, aquarium.height);
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

                long fatherId = fishA.getGender().equals(MALE) ? fishA.getId() : fishB.getId();
                long motherId = fishA.getGender().equals(FEMALE) ? fishB.getId() : fishA.getId();
                if (checkCoordinate(xA, xB, yA, yB, zA, zB) &&
                        !fishA.getGender().equals(fishB.getGender()) &&
                        (fishA.getFatherId() == fatherId)) {
                    System.out.println("This fish is parent 1: " + fishB.getId() + " equals " + motherId);

                }
                if (checkCoordinate(xA, xB, yA, yB, zA, zB) &&
                        !fishA.getGender().equals(fishB.getGender()) &&
                        fishB.getMotherId() == motherId) {
                    System.out.println("This fish is parent 2: " + fishB.getMotherId() + " equals " + fatherId);

                }
                if (checkCoordinate(xA, xB, yA, yB, zA, zB) &&
                        !fishA.getGender().equals(fishB.getGender()) &&
                        fishA.getFatherId() != fatherId &&
                        fishA.getMotherId() != motherId &&
                        fishB.getFatherId() != fatherId &&
                        fishB.getMotherId() != motherId) {
                    Gender gender = random.nextBoolean() ? MALE : FEMALE;
                    Coordinate coordinate = new Coordinate(xA, yA, zA);
                    long fId = fishA.getGender().equals(MALE) ? fishA.getId() : fishB.getId();
                    long mId = fishA.getGender().equals(FEMALE) ? fishA.getId() : fishB.getId();
                    String parent = "Father: " + fId + ", Mother " + mId;
//                    MoveType moveType = MoveType.values()[random.nextInt(MoveType.values().length)];
                    Fish fish = new Fish(gender, coordinate, parent, fatherId, motherId);

                    fishList.add(fish);
                    System.out.println("The fish met at the coordinate: ["
                            + fishA.getPosition().getX() + ", "
                            + fishA.getPosition().getY() + ", "
                            + fishA.getPosition().getZ() + "] " +
                            "This fish ID: " + fish.getId() +
                            " This fish is gender:"
                            + fish.getGender() + ", lifespan: "
                            + fish.getLifespan() + " Current coordinate: [" + fish.getPosition().getX() + ", "
                            + fish.getPosition().getY() + ", "
                            + fish.getPosition().getZ() + "] " + ", This fish parent: "
                            + fish.getParent());

                    if (checkFullness()) {
                        fishList.remove(fish);
                        System.out.println("Aquarium is full");
                        break;
                    }
                }
            }


        }
    }

    private boolean checkAquariumEmpty() {
        return fishList.size() == 0;
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

    public boolean checkFullness() {
        long sizeAquarium = (long) width * length * height;
        long countFishSize = 0;
        for (Fish fish : fishList) {
            countFishSize += fish.getSize();
            if (countFishSize >= sizeAquarium) {
                return true;
            }
        }
        return false;
    }
}


