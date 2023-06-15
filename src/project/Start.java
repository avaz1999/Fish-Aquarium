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
        int length = 2;
        int height = 5;

        createAquarium(weight, length, height);
    }


    public static void createAquarium(int width, int length, int height) {
        aquarium.setWidth(width);
        aquarium.setLength(length);
        aquarium.setHeight(height);

        int maleFish = random.nextInt(10) + 1;  //Generate random male fish
        int femaleFish = random.nextInt(10) + 1;  //Generate random female fish

        Fish.createFish(maleFish,Gender.MALE);  // Add male fish
        Fish.createFish(femaleFish,Gender.FEMALE);  // ADd female fish

        aquarium.simulate();
    }


}
