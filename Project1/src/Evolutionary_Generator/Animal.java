package Evolutionary_Generator;

import java.util.LinkedList;
import java.util.Random;
/*
* this class embodies the idea of animals in our world and is responsible for implementation of
* life - imitating behaviour such as: moving, reproduction*/
public class Animal {

    public static int defaultStartingEnergy;
    private MapDirection orientation;
    public Vector2d position;
    private AbstractWorldMap map;
    private LinkedList<IPositionChangeObserver> maps = new LinkedList<>();
    public int energy;
    public Genes genes;
    private Random r;
    public int lifeLength;
    public float numberOfChildren;
    public Animal DescendantOfTraced;
    public Animal(AbstractWorldMap map, Vector2d position, int startingEnergy, int[] tab){
        r = new Random();
        map.registerAsObserver(this);
        this.map=map;
        this.position=position;
        this.orientation=MapDirection.getRandomDirection(r);
        this.energy = startingEnergy;
        this.genes = new Genes(tab);
        this.lifeLength = 0;
        this.numberOfChildren = 0;
        this.DescendantOfTraced = null;
    }
    public void addObserver(IPositionChangeObserver observer) {
        maps.add(observer);
    }
    public void turn(int movementIndex){
        for(int i = 0; i<movementIndex; i++){
            this.orientation = this.orientation.next();
        }
    }
    public void move(){
        int moveIndex = this.genes.genes[Math.abs(r.nextInt())%32];
        this.turn(moveIndex);
        Vector2d oldPosition = this.position;
        Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
        newPosition.x=Math.abs(newPosition.x%this.map.width);
        newPosition.y=Math.abs(newPosition.y%this.map.height);
        this.position= newPosition;
        this.energy -= this.map.moveEnergy;
        positionChanged(oldPosition, newPosition);

    }

    public static Animal reproductionAct(Animal a1, Animal a2){
        int index1 = Math.abs(a1.r.nextInt())%30;
        int index2 = index1+ 1 + Math.abs(a1.r.nextInt())%(30 - index1);
        int[] childGenes = new int[32];
        for(int i = 0; i<=index1;i++){
            childGenes[i] = a1.genes.genes[i];
        }
        for(int i = index1 + 1; i<=index2;i++){
            childGenes[i] = a2.genes.genes[i];
        }
        for(int i = index2 + 1; i<32;i++){
            childGenes[i] = a1.genes.genes[i];
        }
        int childEnergy = (a1.energy + a2.energy)/4;
        a1.energy = (a1.energy * 3)/4;
        a2.energy = (a2.energy * 3)/4;
        a1.numberOfChildren++;
        a2.numberOfChildren++;
        Animal newAnimal = new Animal(a1.map, a1.position, childEnergy, childGenes);
        a1.map.tracer.updateDescendants(a1, a2, newAnimal);
        return newAnimal;

    }
    public Vector2d getPosition(){
        return this.position;
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver it : maps){
            it.positionChanged(oldPosition, newPosition, this);
        }
    }
}

