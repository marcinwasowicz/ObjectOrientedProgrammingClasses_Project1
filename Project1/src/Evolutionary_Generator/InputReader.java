package Evolutionary_Generator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class InputReader {

    private int width;
    private int height;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private double jungleRatio;
    private EngineCreator creator;
    private int numberOfAnimals;
    /* this class enables user to manipulate application input data in a code-less way. In other words, it simply
    * unpacks json file into a simulation creator instance*/
    public InputReader(){
    }

    public void parse(String filePath) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));
        JSONObject initialData = (JSONObject) obj;

        this.height = (int)(long) initialData.get("height");
        this.width = (int)(long) initialData.get("width");
        this.startEnergy = (int)(long) initialData.get("startEnergy");
        this.moveEnergy = (int)(long) initialData.get("moveEnergy");
        this.plantEnergy = (int)(long) initialData.get("plantEnergy");
        this.jungleRatio = (double) initialData.get("jungleRatio");
        this.numberOfAnimals = (int)(long) initialData.get("numberOfAnimals");
        this.creator = new EngineCreator(width, height, startEnergy,numberOfAnimals,plantEnergy,moveEnergy,jungleRatio);
    }

    public SimulationEngine createEngine(){
        return this.creator.createEngine();
    }
}
