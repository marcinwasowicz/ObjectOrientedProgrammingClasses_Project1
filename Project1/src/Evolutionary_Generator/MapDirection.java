package Evolutionary_Generator;

import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    SOUTH,
    SOUTH_EAST,
    WEST,
    SOUTH_WEST,
    NORTH_WEST,
    EAST;

    public String toString() {
        switch (this) {
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            case WEST:
                return "Zachód";
            case EAST:
                return "Wschód";
        }
        return "ddd";
    }
    public MapDirection next(){
        switch(this){
            case NORTH:
                return NORTH_EAST;
            case SOUTH:
                return SOUTH_WEST;
            case WEST:
                return NORTH_WEST;
            case EAST:
                return SOUTH_EAST;
            case NORTH_EAST:
                return EAST;
            case NORTH_WEST:
                return NORTH;
            case SOUTH_WEST:
                return WEST;
            case SOUTH_EAST:
                return SOUTH;
        }
        return NORTH;
    }
    public MapDirection previous(){
        switch(this){

            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            case EAST:
                return NORTH;
        }
        return this;
    }
    public Vector2d toUnitVector(){
        switch(this){

            case NORTH:
                return new Vector2d(0,1);
            case SOUTH:
                return new Vector2d(0,-1);
            case WEST:
                return new Vector2d(-1,0);
            case EAST:
                return new Vector2d(1,0);
            case NORTH_EAST:
                return new Vector2d(1,1);
            case NORTH_WEST:
                return new Vector2d(-1,1);
            case SOUTH_EAST:
                return new Vector2d(1,-1);
            case SOUTH_WEST:
                return new Vector2d(-1,-1);
        }
        return new Vector2d(0,0);
    }
    public static MapDirection getRandomDirection(Random random){
        int num = random.nextInt(8);
        switch (num){
            case 1:
                return SOUTH;
            case 2:
                return WEST;
            case 3:
                return EAST;
            case 4:
                return NORTH_EAST;
            case 5:
                return NORTH_WEST;
            case 6:
                return SOUTH_EAST;
            case 7:
                return SOUTH_WEST;
            default:
                return NORTH;
        }
    }
}

