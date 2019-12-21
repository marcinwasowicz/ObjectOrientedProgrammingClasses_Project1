package Evolutionary_Generator;

import java.util.Random;
/* this class is responsible for creating initial animals (adams and eves) and placing the on the grass field, before
* simulation can be launched*/
public class AnimalSpawner {

    private GrassField field;
    private int numberToGenerate;
    private int startingEnergy;

    private Random r;
    public AnimalSpawner(GrassField field, int numberToGenerate, int startingEnergy){
        this.field = field;
        this.numberToGenerate = numberToGenerate;
        this.startingEnergy = startingEnergy;
        this.r = new Random();
    }

    private Animal generateAnimal(){
        int positionX = Math.abs(r.nextInt())%field.width;
        int positionY = Math.abs(r.nextInt())%field.height;

        Vector2d position = new Vector2d(positionX, positionY);

        int[] genes = new int[32];
        for(int i = 0; i<32; i++){
            genes[i] = Math.abs(r.nextInt())%8;
        }

        return new Animal(field, position, startingEnergy, genes);
    }

    public void spawnAnimals(){
        for(int i = 0; i<numberToGenerate; i++){
            Animal animal = generateAnimal();
            field.place(animal);
        }
    }
}
