package Evolutionary_Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/* this class provides an API for a HashSet of Vectors capable of giving a random element in O(1) time always.
*  */
public class RandomHashSet {

    private HashMap<Vector2d, Integer> dict;
    private ArrayList<Vector2d> vectorOfChoices;
    private Random r;

    public RandomHashSet(){
        this.r = new Random();
        this.dict = new HashMap<>();
        this.vectorOfChoices = new ArrayList<>();
    }

    public void add(Vector2d vec){
        if(this.dict.containsKey(vec)){
            return;
        }
        this.vectorOfChoices.add(vec);
        this.dict.put(vec, this.vectorOfChoices.size()-1);
    }

    public void remove(Vector2d vec){
        if(!this.dict.containsKey(vec)){
            return;
        }
        int index = this.dict.get(vec);
        Vector2d lastVector = vectorOfChoices.get(vectorOfChoices.size()-1);
        vectorOfChoices.add(index, lastVector);
        dict.put(lastVector, index);
        dict.remove(vec);
        vectorOfChoices.remove(vectorOfChoices.size()-1);
    }

    public Vector2d getRandom(){
        int index = r.nextInt(vectorOfChoices.size()-1);
        return vectorOfChoices.get(index);
    }

    public boolean isEmpty(){
        return dict.isEmpty();
    }
}
