package Evolutionary_Generator;

import javax.swing.*;
/*this is an extension of JLabel class that makes it able to store an information about its position of an animation
* window*/
public class VectorLabel extends JLabel {
    private Vector2d position;

    public VectorLabel(Vector2d position){
        super();
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
