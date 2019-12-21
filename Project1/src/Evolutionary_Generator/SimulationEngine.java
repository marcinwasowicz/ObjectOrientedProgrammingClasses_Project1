package Evolutionary_Generator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
/*this class is used to synchronize updating grass field state and its visual rendering. It si also responsible for
* reacting to keyboard events*/
public class SimulationEngine implements Runnable {

    private GrassField field;
    private Window window;
    private boolean running;
    private OutputWriter writer;
    private boolean markingDominatingGenomes;

    public SimulationEngine(GrassField field, Window window){
        this.field = field;
        this.window = window;
        this.running = true;
        this.window.getFrame().addKeyListener(new KeyInput());
        this.writer = new OutputWriter(this.field);
        this.markingDominatingGenomes = false;
    }

    private class KeyInput implements KeyListener {
        public KeyInput(){
            super();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_S){
                running = !running;
                markingDominatingGenomes = false;
            }
            else if(!running && key == KeyEvent.VK_F){
                try {
                    writer.WriteFile();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
            else if(!running && key == KeyEvent.VK_G){
                markingDominatingGenomes = !markingDominatingGenomes;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
        }

    }
    private void markDominatingGenomes(){
        for(Animal it : field.animalList){
            if (it.genes.equals(field.getMostFrequentGene())){
                window.visualizer.getLabels().get(it.getPosition()).setBackground(Color.BLUE);
            }
        }
    }

    @Override
    public void run(){
        double amountOfTicks = 7;
        double nanoSecond = 1000000000/amountOfTicks;
        double delta = 0.0;
        long lastTime = System.nanoTime();
        while(true){
            if(running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / nanoSecond;
                lastTime = now;
                while (delta >= 1.0) {
                    field.day();
                    delta--;
                }
            }
            else {
                lastTime = System.nanoTime();
            }
            if(!markingDominatingGenomes) {
                window.render();
            }else{
                markDominatingGenomes();
            }
        }
    }
}
