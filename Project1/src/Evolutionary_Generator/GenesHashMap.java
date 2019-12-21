package Evolutionary_Generator;

import java.util.HashMap;
import java.util.Map;
/* this class is a dictionary API dedicated to storing genotypes as keys, and their counts as values */
public class GenesHashMap {

    private HashMap<Genes, Integer> dictionary;
    private int greatestFrequency;
    private Genes bestGene;

    public GenesHashMap(){
        this.dictionary = new HashMap<>();
        this.greatestFrequency = 0;
        this.bestGene = new Genes(new int[32]);
    }

    public void addGene(Genes genes){
        if(this.dictionary.containsKey(genes)){
            int i = this.dictionary.get(genes);
            i++;
            this.dictionary.put(genes,i);
        }
        else{
            this.dictionary.put(genes, 1);
        }
    }

    public void removeGene(Genes genes){
        int i = this.dictionary.get(genes);
        i--;
        if(i>0){
            this.dictionary.put(genes,i);
        }
        else{
            this.dictionary.remove(genes);
        }
    }

    public void updateBestGene(){
        this.greatestFrequency = 0;
        this.bestGene = new Genes(new int[32]);
        for(Map.Entry<Genes, Integer> it : this.dictionary.entrySet()){
            if(it.getValue() > this.greatestFrequency){
                this.bestGene = it.getKey();
                this.greatestFrequency = it.getValue();
            }
        }
    }

    public int getGreatestFrequency(){
        return this.greatestFrequency;
    }

    public Genes getBestGene(){
        return this.bestGene;
    }
}
