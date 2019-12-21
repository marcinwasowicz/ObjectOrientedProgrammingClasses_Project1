package Evolutionary_Generator;

import org.json.simple.parser.ParseException;

import java.io.IOException;
/*this class provides us with a main function, in which we pares our input file, and create two parallel simulations*/
public class World {
    public static void main(String[] args) throws IOException, ParseException {
        InputReader reader = new InputReader();
        reader.parse("C:\\Users\\marci\\Desktop\\inputData.json");
        SimulationEngine engine1 = reader.createEngine();
        SimulationEngine engine2 = reader.createEngine();
        Thread simulation1 = new Thread(engine1);
        Thread simulation2 = new Thread(engine2);
        simulation1.start();
        simulation2.start();
    }
}
