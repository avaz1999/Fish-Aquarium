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
        int length = 1;
        int height = 5;

        createAquarium(weight, length, height);
    }

    public static void createAquarium(int width, int length, int height) {
        aquarium.setWidth(width);
        aquarium.setLength(length);
        aquarium.setHeight(height);

        int maleFish = random.nextInt(10) + 1;  //Generate random male fish
        int femaleFish = random.nextInt(10) + 1;  //Generate random female fish
        long size = (long) width * length * height;
        long count = 0;
        for (int i = 0; i < maleFish; i++) { // Add male fish
            Coordinate coordinate = Coordinate.createCoordinate(aquarium.getWidth(), aquarium.getLength(), aquarium.getHeight());
            Fish fish = new Fish(Gender.MALE, coordinate, 5L);
            for (Fish countFish : aquarium.getFishList()) {
                count += countFish.getSize();
                if (count >= size){
                    System.out.println("This aquarium is full");
                    break;
                }
            }
            aquarium.addFish(fish);
        }
        for (int i = 0; i < femaleFish; i++) {  // Add female fish
            Coordinate coordinate = Coordinate.createCoordinate(aquarium.getWidth(), aquarium.getLength(), aquarium.getHeight());
            Fish fish = new Fish(Gender.FEMALE, coordinate, 5L);
            for (Fish countFish : aquarium.getFishList()) {
                count += countFish.getSize();
                if (count >= size){
                    System.out.println("This aquarium is full");
                    break;
                }
            }
            aquarium.addFish(fish);
        }
        aquarium.simulate();
    }


}
