package Evolutionary_Generator;

import java.util.*;
/*
* This abstract class enables us to handle basic concepts about our world, and separate them from
* more specialized GrassField class*/
public abstract class AbstractWorldMap implements IPositionChangeObserver {
    public HashMap<Vector2d, LinkedList<Animal>> animals;
    public ArrayList<Animal> animalList;
    public int width;
    public int height;
    public int moveEnergy;
    public double totalChildrenCount;
    public Tracer tracer;
    protected long epoch;

    public AbstractWorldMap(int width, int height, int moveEnergy){
        this.width = width;
        this.height = height;
        this.moveEnergy = moveEnergy;
        this.animalList=new ArrayList<>();
        this.animals=new HashMap<>();
        this.totalChildrenCount = 0.0;
        this.tracer = new Tracer(this);
        this.epoch = 0;
    }
    //moving animals around
    public void runMovements(){
        for(Animal it : animalList){
            it.move();
            it.lifeLength++;
        }
    }
    //placing new animals
    public void place(Animal animal){
        if(!this.animals.containsKey(animal.getPosition())){
            this.animals.put(animal.getPosition(), new LinkedList<>());
        }
        this.animals.get(animal.getPosition()).add(animal);
        this.animalList.add(animal);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        LinkedList<Animal> temp = animals.get(oldPosition);
        temp.remove(animal);
        if(animals.get(oldPosition).size() == 0){
            animals.remove(oldPosition);
        }
        if(!this.animals.containsKey(newPosition)){
            this.animals.put(newPosition, new LinkedList<>());
        }
        this.animals.get(newPosition).add(animal);
    }
    public long getEpoch(){
        return this.epoch;
    }

    public void registerAsObserver(Animal animal){
        animal.addObserver(this);
    }
}

