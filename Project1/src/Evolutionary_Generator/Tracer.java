package Evolutionary_Generator;
/*
* this class stores information about an animal that is being traced and controls animal's reproduction
* to search for new descendants of an animal that has been chosen for tracing*/
public class Tracer {

    private Animal tracedAnimal;
    private int tracedAnimalChildren;
    private int tracedAnimalDescendants;
    private long deathEpoch;
    private AbstractWorldMap field;

    public Tracer(AbstractWorldMap field){
        this.tracedAnimal = null;
        this.tracedAnimalChildren = -1;
        this.tracedAnimalDescendants = -1;
        this.deathEpoch = -1;
        this.field = field;
    }

    public boolean isTracing(){
        return this.tracedAnimal != null;
    }

    public Animal getTracedAnimal() {
        return tracedAnimal;
    }

    public void setTracedAnimal(Animal animal){
        this.tracedAnimal = animal;
        this.tracedAnimalDescendants = 0;
        this.tracedAnimalChildren = 0;
        this.deathEpoch = -1;
    }


    public void updateDescendants(Animal parent1, Animal parent2, Animal child){
        if(this.tracedAnimal == null){
            return;
        }
        if(parent1 == this.tracedAnimal || parent2 == this.tracedAnimal){
            this.tracedAnimalChildren++;
            child.DescendantOfTraced = this.tracedAnimal;
            return;
        }
        if(parent1.DescendantOfTraced == this.tracedAnimal || parent2.DescendantOfTraced == this.tracedAnimal){
            child.DescendantOfTraced = this.tracedAnimal;
            this.tracedAnimalDescendants++;
        }
    }

    public void updateData(){
        if(this.tracedAnimal == null || this.tracedAnimal.energy>0) {
            return;
        }
        this.deathEpoch = this.field.getEpoch();
        this.tracedAnimal = null;
    }

    public int getTracedAnimalChildren(){
        return tracedAnimalChildren;
    }

    public int getTracedAnimalDescendants(){
        return tracedAnimalDescendants;
    }

    public long getDeathEpoch(){
        return this.deathEpoch;
    }
}
