package Evolutionary_Generator;
/*
* this class is responsible for keeping and updating average statistics needed for file writing operation*/
public class AverageDataHolder {
    private GrassField field;
    public double averageNumberOfAnimals;
    public double averageNumberOfPlants;
    public double averageLifeExpectancy;
    public double averageAverageChildrenCount;
    public Genes mostFrequentGenotype;
    public int mostFrequentGenotypeFrequency;
    public long epoch;

    public AverageDataHolder(GrassField field){
        this.field = field;
        this.averageAverageChildrenCount = 0;
        this.averageLifeExpectancy = 0;
        this.averageNumberOfAnimals = 0;
        this.averageNumberOfPlants = 0;
        this.mostFrequentGenotypeFrequency = 0;
        this.mostFrequentGenotype = null;
    }

    public void updateAverageStatistics(){
        epoch = this.field.getEpoch();
        if(epoch == 0){
            return;
        }
        averageNumberOfPlants = (averageNumberOfPlants*(epoch-1) + field.getNumPlants())/epoch;
        averageNumberOfAnimals  = (averageNumberOfAnimals*(epoch-1)+field.getNumAnimals())/epoch;
        averageLifeExpectancy = (averageLifeExpectancy*(epoch-1)+field.getLifeExpectancy())/epoch;
        averageAverageChildrenCount = (averageAverageChildrenCount*(epoch-1)+field.getChildrenCount())/epoch;
        if(field.getMostFrequentGeneFrequency() > mostFrequentGenotypeFrequency){
            mostFrequentGenotypeFrequency = field.getMostFrequentGeneFrequency();
            mostFrequentGenotype = field.getMostFrequentGene();
        }
    }

}
