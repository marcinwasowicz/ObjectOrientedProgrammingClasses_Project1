package Evolutionary_Generator;
/*
* this class is responsible for calculating parameters of a jungle area on grass field*/
public class Jungle {
    private final int lowerVertical;
    private final int lowerHorizontal;
    private final int upperVertical;
    private final int upperHorizontal;

    public Jungle(int width, int height, double jungleRatio){
        int jungleWidth = (int) Math.round(width * Math.sqrt(jungleRatio));
        this.lowerVertical = (width - jungleWidth) / 2;
        this.upperVertical = this.lowerVertical + jungleWidth - 1;
        int jungleHeight = (int) Math.round(height * Math.sqrt(jungleRatio));
        this.lowerHorizontal = (height - jungleHeight) / 2;
        this.upperHorizontal = lowerVertical + jungleHeight - 1;
    }

    public int getLowerHorizontal() {
        return lowerHorizontal;
    }

    public int getLowerVertical() {
        return lowerVertical;
    }

    public int getUpperHorizontal() {
        return upperHorizontal;
    }

    public int getUpperVertical() {
        return upperVertical;
    }

}
