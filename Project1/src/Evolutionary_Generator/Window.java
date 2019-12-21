package Evolutionary_Generator;
import javax.swing.*;
import java.awt.*;
/* this class graphically displays animation*/
public class Window {

    public int width;
    public int height;
    public GrassField field;
    public GraphicsVisualizer visualizer;
    private JFrame frame;

    private String defaultNumAnimalsString = "Number Of Animals: ";
    private String defaultNumPlantsString = "Number Of Plants: ";
    private String defaultNumOfEpochPassed = "Number Of Epochs Passed: ";
    private String defaultLifeExpectancyString = "Life Expectancy: ";
    private String defaultAverageChildrenCountString = "Average Children Count: ";
    private String defaultBestGeneDataString = "Best Gene: ";
    private String defaultRecentlyTracedAnimalDataString = "Recently Traced Animal Data: ";
    private String defaultRecentlyTracedAnimalEndingString = "--  --  --";

    private JLabel numAnimals;
    private JLabel numPlants;
    private JLabel numEpochPassed;
    private JLabel lifeExpectancy;
    private JLabel childrenAverageCount;
    private JLabel bestGene;
    private JLabel recentlyTracedAnimal;

    public Window(int width, int height, GrassField field){
        this.width = width;
        this.height = height;
        this.field = field;

        Dimension dim = new Dimension(width, height);
        JPanel statistics = new JPanel();
        statistics.setLayout(new GridLayout(4,1));
        numAnimals = new JLabel(defaultNumAnimalsString);
        numPlants = new JLabel(defaultNumPlantsString);
        numEpochPassed = new JLabel(defaultNumOfEpochPassed);
        lifeExpectancy = new JLabel(defaultLifeExpectancyString);
        childrenAverageCount = new JLabel(defaultAverageChildrenCountString);
        bestGene = new JLabel(defaultBestGeneDataString);
        recentlyTracedAnimal = new JLabel(defaultRecentlyTracedAnimalDataString+defaultRecentlyTracedAnimalEndingString);
        statistics.add(numAnimals);
        statistics.add(numPlants);
        statistics.add(numEpochPassed);
        statistics.add(lifeExpectancy);
        statistics.add(childrenAverageCount);
        statistics.add(bestGene);
        statistics.add(recentlyTracedAnimal);
        frame = new JFrame("Evolutionary Generator of grass field "+this.field.getGrassFieldID());
        frame.setLayout(new BorderLayout());
        visualizer = new GraphicsVisualizer(field);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMaximumSize(dim);
        frame.setMaximumSize(dim);
        frame.setPreferredSize(dim);
        frame.add(visualizer, BorderLayout.CENTER);
        frame.add(statistics, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void render(){
        numAnimals.setText(defaultNumAnimalsString + this.field.getNumAnimals());
        numPlants.setText(defaultNumPlantsString + this.field.getNumPlants());
        numEpochPassed.setText(defaultNumOfEpochPassed + this.field.getEpoch());
        lifeExpectancy.setText(defaultLifeExpectancyString + this.field.getLifeExpectancy());
        childrenAverageCount.setText(defaultAverageChildrenCountString + this.field.getChildrenCount());
        bestGene.setText(defaultBestGeneDataString + this.field.getMostFrequentGeneFrequency() +" "+ this.field.getMostFrequentGene().toString());
        recentlyTracedAnimal.setText(defaultRecentlyTracedAnimalDataString + this.field.tracer.getTracedAnimalChildren() + " " + this.field.tracer.getTracedAnimalDescendants() + " " + this.field.tracer.getDeathEpoch());
        visualizer.render();
    }
    public JFrame getFrame(){
        return this.frame;
    }
}
