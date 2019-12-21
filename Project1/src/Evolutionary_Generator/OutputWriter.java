package Evolutionary_Generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/*this class is responsible for creating instances of average statistics files*/
public class OutputWriter {

    private GrassField field;
    private int autoIncrementFileID;
    private String defaultFileName = "statistics_file_of_grass_";
    private String defaultFileExtension = ".txt";

    public OutputWriter(GrassField field){
        this.field = field;
    }

    public void WriteFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(defaultFileName+this.field.getGrassFieldID()+"_"+autoIncrementFileID+defaultFileExtension, "UTF-8");
        writer.println("Number Of Epoch: " + this.field.getEpoch());
        writer.println("Number Of Animals: " + this.field.averageDataHolder.averageNumberOfAnimals);
        writer.println("Number Of Plants: " + this.field.averageDataHolder.averageNumberOfPlants);
        writer.println("Life Expectancy: " + this.field.averageDataHolder.averageLifeExpectancy);
        writer.println("Average Children Count: " + this.field.averageDataHolder.averageAverageChildrenCount);
        writer.println("Best Gene: " + this.field.averageDataHolder.mostFrequentGenotypeFrequency + " " +this.field.averageDataHolder.mostFrequentGenotype);
        writer.close();
        autoIncrementFileID++;
    }
}
