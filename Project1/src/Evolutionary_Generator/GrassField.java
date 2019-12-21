package Evolutionary_Generator;

import java.util.*;
/* this class serves as a world for animals and grass*/
public class GrassField extends AbstractWorldMap {
    public HashSet<Vector2d> grasses;
    public static int autoIncrementGrassFieldID = 0;
    private int grassFieldID;
    private int plantEnergy;
    private double jungleRatio;
    private PlantsSpawner plantsSpawner;
    private DataHolder holder;
    private double totalChildrenCount;
    public AverageDataHolder averageDataHolder;

    private class  DataHolder{
        /*this class serves as a storage of current statistics about our world*/
        public long numAnimals;
        public long numPlants;
        public double lifeExpectancy;
        public double childrenAverageCount;
        public GenesHashMap genesDictionary;

        public DataHolder(){
            this.numAnimals = 0;
            this.numPlants = 0;
            this.lifeExpectancy = 0;
            this.childrenAverageCount = 0.0;
            this.genesDictionary = new GenesHashMap();
        }
    }

    public GrassField(int width, int height, int plantEnergy, int moveEnergy, double jungleRatio){
        super(width, height, moveEnergy);
        this.plantEnergy = plantEnergy;
        this.grasses = new HashSet<>();
        this.jungleRatio = jungleRatio;
        this.grassFieldID = GrassField.autoIncrementGrassFieldID;
        GrassField.autoIncrementGrassFieldID++;
        epoch = 0;
        Jungle jungle = new Jungle(width, height, jungleRatio);
        for(int i = jungle.getLowerVertical(); i <= jungle.getUpperVertical();i++){
            for(int j = jungle.getLowerHorizontal(); j<=jungle.getUpperHorizontal();j++){
                grasses.add(new Vector2d(i, j));
            }
        }
        this.plantsSpawner = new PlantsSpawner(this);
        this.holder = new DataHolder();
        this.averageDataHolder = new AverageDataHolder(this);
    }
    private void cleanCorps(){
        double life = 0;
        LinkedList<Animal> toClean = new LinkedList<>();
        for(Animal it : animalList){
            if(it.energy <=0 ){
                toClean.add(it);
                life += it.lifeLength;
                totalChildrenCount -= it.numberOfChildren;
                this.holder.genesDictionary.removeGene(it.genes);
            }
        }
        for(Animal it : toClean){
            animalList.remove(it);
            this.animals.get(it.getPosition()).remove(it);
            if(this.animals.get(it.getPosition()).size() == 0){
                this.animals.remove(it.getPosition());
            }
        }
        if(toClean.size()>0){
            this.holder.lifeExpectancy = life/toClean.size();
        }
        this.holder.numAnimals -= toClean.size();
    }
    private void eat(){
        LinkedList<Vector2d> eatenGrasses = new LinkedList<>();
        for(Vector2d it : grasses){
            if(this.animals.containsKey(it)){
                this.animals.get(it).getFirst().energy += this.plantEnergy;
                eatenGrasses.add(it);
            }
        }
        for(Vector2d it : eatenGrasses){
            plantsSpawner.informOfEating(it);
        }
    }
    public void day(){
        cleanCorps();
        runMovements();
        eat();
        reproduce();
        growGrass();
        updateStatistics();
        epoch++;
        averageDataHolder.updateAverageStatistics();
    }
    private void growGrass(){
        plantsSpawner.spawnPlants();
    }
    private void updateStatistics(){
        this.holder.numPlants = this.grasses.size();
        if(this.holder.numAnimals>0) {
            this.holder.childrenAverageCount = totalChildrenCount / this.holder.numAnimals;
        }
        this.holder.genesDictionary.updateBestGene();
        this.tracer.updateData();
    }
    @Override
    public void place(Animal animal){
        super.place(animal);
        this.holder.numAnimals++;
        this.holder.genesDictionary.addGene(animal.genes);
    }
    public void reproduce(){
        LinkedList<Animal> newAnimals = new LinkedList<>();
        for(Map.Entry<Vector2d, LinkedList<Animal>> it : animals.entrySet()){
            if(it.getValue().size() > 1){
                LinkedList<Animal> tempList = it.getValue();
                Animal strongest = tempList.getFirst();
                for(Animal it2 : tempList){
                    if(it2.energy > strongest.energy){
                        strongest = it2;
                    }
                }
                Animal secondStrongest = tempList.getFirst();
                for(Animal it2 : tempList){
                    if(it2.energy > secondStrongest.energy || secondStrongest == strongest && it2!=strongest ){
                        secondStrongest = it2;
                    }
                }
                if(secondStrongest.energy>=Animal.defaultStartingEnergy/2
                && strongest.energy>=Animal.defaultStartingEnergy/2) {
                    newAnimals.add(Animal.reproductionAct(secondStrongest, strongest));
                    totalChildrenCount += 2;
                }
            }
        }
        for(Animal it : newAnimals){
            this.place(it);
        }
    }
    public int getGrassFieldID(){
        return this.grassFieldID;
    }
    public long getNumAnimals(){
        return this.holder.numAnimals;
    }
    public long getNumPlants(){
        return this.holder.numPlants;
    }
    public long getEpoch(){
        return epoch;
    }
    public double getLifeExpectancy(){
        return this.holder.lifeExpectancy;
    }
    public double getChildrenCount(){
        return this.holder.childrenAverageCount;
    }
    public int getMostFrequentGeneFrequency(){
        return this.holder.genesDictionary.getGreatestFrequency();
    }
    public Genes getMostFrequentGene(){
        return this.holder.genesDictionary.getBestGene();
    }
}
