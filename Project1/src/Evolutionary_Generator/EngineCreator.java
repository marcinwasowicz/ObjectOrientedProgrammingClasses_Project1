package Evolutionary_Generator;
/*this class takes input values to create instances of simulations*/
public class EngineCreator {

    private int width;
    private int height;
    private int startingEnergy;
    private int numberOfAnimalsAtBeginning;
    private int plantEnergy;
    private int moveEnergy;
    private double jungleRatio;

    public EngineCreator(int width, int height, int startingEnergy, int numberOfAnimalsAtBeginning, int plantEnergy, int moveEnergy, double jungleRatio){
        this.width = width;
        this.height = height;
        this.startingEnergy = startingEnergy;
        this.numberOfAnimalsAtBeginning = numberOfAnimalsAtBeginning;
        this.plantEnergy = plantEnergy;
        this.moveEnergy = moveEnergy;
        this.jungleRatio = jungleRatio;
        Animal.defaultStartingEnergy = startingEnergy;
    }

    public SimulationEngine createEngine(){
        GrassField field = new GrassField(width, height,plantEnergy, moveEnergy, jungleRatio);
        AnimalSpawner spawner = new AnimalSpawner(field, numberOfAnimalsAtBeginning, startingEnergy);
        spawner.spawnAnimals();
        Window window = new Window(700, 700, field);
        SimulationEngine simulationEngine = new SimulationEngine(field, window);
        return simulationEngine;
    }
}
