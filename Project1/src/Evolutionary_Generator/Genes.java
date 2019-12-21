package Evolutionary_Generator;
/* this class provides us with an abstraction of genes array, so that an animals' genotype
* can be used as a key in dictionary collection, which is needed for some statistics manipulation operations*/
import java.util.Arrays;

public class Genes {
    public int[] genes;

    public Genes(int[] genes){
        this.genes = genes;
    }

    @Override
    public String toString(){
        String res = "";
        for(int it : this.genes){
            res += it;
        }
        return res;
    }

    @Override
    public int hashCode(){
        Arrays.sort(this.genes);
        return Arrays.hashCode(this.genes);
    }

    public boolean equals(Genes genes){
        return Arrays.equals(this.genes, genes.genes);
    }
}
