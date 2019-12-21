package Evolutionary_Generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
/* this class is responsible for drawing animation and making squares responsive to mose clicking*/
public class GraphicsVisualizer extends JPanel {

    private GrassField field;
    private HashMap<Vector2d, VectorLabel> labels;

    public GraphicsVisualizer(GrassField field){
        this.labels = new HashMap<>();
        this.field = field;
        this.setLayout(new GridLayout(field.width, field.height));
        for(int i = 0; i<field.width;i++){
            for(int j = 0; j<field.height;j++){
                Vector2d temp = new Vector2d(i,j);
                VectorLabel label = new VectorLabel(temp);
                label.addMouseListener(new MouseInput());
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
                this.add(label);
                labels.put(temp, label);
            }
        }
    }

    public void render(){
        for(int i = 0; i<field.width;i++){
            for(int j = 0; j<field.height;j++){
                Vector2d temp = new Vector2d(i,j);
                VectorLabel label = labels.get(temp);
                if(field.animals.containsKey(temp)){
                    label.setOpaque(true);
                    if(this.field.tracer.isTracing() && this.field.tracer.getTracedAnimal().position.equals(temp)){
                        label.setBackground(Color.PINK);
                    }
                    else {
                        label.setBackground(Color.RED);
                    }
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else if(field.grasses.contains(temp)){
                    label.setOpaque(true);
                    label.setBackground(Color.GREEN);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else{
                    label.setOpaque(true);
                    label.setBackground(Color.BLACK);
                }
            }
        }
    }
    public HashMap<Vector2d, VectorLabel> getLabels(){
        return this.labels;
    }
    private class MouseInput implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            VectorLabel label = (VectorLabel) e.getComponent();
            Vector2d temp = label.getPosition();
            if(field.animals.containsKey(temp)) {
                field.tracer.setTracedAnimal(field.animals.get(temp).getFirst());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            return;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            return;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            return;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            return;
        }
    }
}
