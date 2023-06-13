package project;

import project.Aquarium;
import project.Coordinate;
import project.Fish;
import project.enums.Gender;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Start {
    public static Aquarium aquarium = new Aquarium();
    private static final Random random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        int weight = 2;
        int length = 20;
        int height = 5;

        createAquarium(weight, length, height);
    }

    static long count = 0;

    public static void createAquarium(int width, int length, int height) {
        aquarium.setWidth(width);
        aquarium.setLength(length);
        aquarium.setHeight(height);

        int maleFish = random.nextInt(100) + 1;  //Generate random male fish
        int femaleFish = random.nextInt(100) + 1;  //Generate random female fish
        long size = (long) width * length * height;
        long count = 0;
        for (int i = 0; i < maleFish; i++) { // Add male fish
            Coordinate coordinate = Coordinate.createCoordinate(aquarium.getWidth(), aquarium.getLength(), aquarium.getHeight());
            Fish fish = new Fish(Gender.MALE, coordinate, "", 0, 0);
            if (checkFull()) {
                System.out.println("The aquarium is filled with : " + count + " fish");
                break;
            }
            aquarium.addFish(fish);
        }
        for (int i = 0; i < femaleFish; i++) {  // Add female fish
            Coordinate coordinate = Coordinate.createCoordinate(aquarium.getWidth(), aquarium.getLength(), aquarium.getHeight());
            Fish fish = new Fish(Gender.FEMALE, coordinate, "", 0, 0);
            if (checkFull()) {
                System.out.println("The aquarium is filled with : " + count + " fish");
                break;
            }
            aquarium.addFish(fish);
        }
        aquarium.simulate();
    }

    public static boolean checkFull() {
        long sizeAquarium = ((long) aquarium.getWidth() * aquarium.getLength() * aquarium.getHeight());
        for (Fish fish : aquarium.getFishList()) {
            count += fish.getSize();
            if (count >= sizeAquarium) {
                return true;
            }
            break;
        }
        return false;
    }


}
