package Evolutionary_Generator;

import java.util.HashSet;
import java.util.Random;
/* this class spawns one random plant on both areas (jungle, desert) each day*/
public class PlantsSpawner {

    private RandomHashSet emptyFieldsDesert;
    private HashSet<Vector2d> grownFieldsJungle;
    private RandomHashSet emptyFieldsJungle;
    private HashSet<Vector2d> grownFieldsDesert;
    private GrassField field;
    private Random r;

    public PlantsSpawner(GrassField field){
        this.field = field;
        this.emptyFieldsDesert = new RandomHashSet();
        this.grownFieldsJungle = new HashSet<>();
        this.grownFieldsDesert = new HashSet<>();
        this.emptyFieldsJungle = new RandomHashSet();
        this.r = new Random();
        for(int i = 0; i<field.width; i++){
            for(int j = 0; j<field.height; j++){
                Vector2d temp = new Vector2d(i,j);
                if(field.grasses.contains(temp)){
                    grownFieldsJungle.add(temp);
                }
                else{
                    emptyFieldsDesert.add(temp);
                }
            }
        }
    }
    public void informOfEating(Vector2d position){
       if(grownFieldsJungle.contains(position)){
           grownFieldsJungle.remove(position);
           emptyFieldsJungle.add(position);
       }
       else{
           grownFieldsDesert.remove(position);
           emptyFieldsDesert.add(position);
       }
       this.field.grasses.remove(position);
    }
    private void spawnRandomElementDesert(){
        if(emptyFieldsDesert.isEmpty()){
            return;
        }
        Vector2d rand = emptyFieldsDesert.getRandom();
        emptyFieldsDesert.remove(rand);
        grownFieldsDesert.add(rand);
        this.field.grasses.add(rand);
    }
    private void spawnRandomElementJungle(){
        if(emptyFieldsJungle.isEmpty()){
            return;
        }
        Vector2d rand = emptyFieldsJungle.getRandom();
        emptyFieldsJungle.remove(rand);
        grownFieldsJungle.add(rand);
        this.field.grasses.add(rand);
    }
    public void spawnPlants(){
        spawnRandomElementDesert();
        spawnRandomElementJungle();
    }
}
