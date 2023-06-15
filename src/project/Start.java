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
        int weight = 5;
        int length = 5;
        int height = 10;

        createAquarium(weight, length, height);
    }


    public static void createAquarium(int width, int length, int height) {
        aquarium.setWidth(width);
        aquarium.setLength(length);
        aquarium.setHeight(height);

        int maleFish = 30;  //random.nextInt(50) + 1;  //Generate random male fish
        int femaleFish = 30;  //random.nextInt(50) + 1;  //Generate random female fish

        Fish.createFish(maleFish,Gender.MALE);  // Add male fish
        Fish.createFish(femaleFish,Gender.FEMALE);  // ADd female fish

        aquarium.simulate();
    }


}
