package Evolutionary_Generator;
/*this is an abstraction of a pair of integers */
public class Vector2d  {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }
    public boolean equals(Object other){
        if(!(other instanceof Vector2d)){
            return false;
        }
        Vector2d obj=(Vector2d)other;
        return (obj.x==this.x)&&(obj.y==this.y);
    }
    @Override
    public int hashCode() {
        return ((x+y)*(x+y+1))/2+y;
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}

