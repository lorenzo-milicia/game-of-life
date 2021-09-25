import processing.core.PApplet;

import java.util.Arrays;


public class ProcessingMain extends PApplet {

    private static final int dimension = 100;
    float t = 0;
    Integer[] cells = new Integer[dimension * dimension];
    PetriDish petriDish;

    public static void main(String[] args) {
        PApplet.main("ProcessingMain");
    }

    public void settings() {
        //fullScreen();
        size(1000, 1000, JAVA2D);

    }

    public void setup() {
        background(0);
        colorMode(HSB, 255);

        for (int i = 0; i < cells.length; i++) {
            cells[i] = round(pow(random(1), 6));
        }

        petriDish = new PetriDish(Arrays.asList(cells), dimension, dimension);
    }

    public void draw() {
        background(0);

        for (int i = 0; i < cells.length; i++) {
            int column = i % dimension;
            int row = i / dimension;
            int size = 1000 / dimension;
            if (cells[i] == 1) {
                fill((column + t / 1000.f) % 255, 255, 255);
                //circle(column * size, row * size, size);
                square(column * size, row * size, size);
            } else {
                fill(0);
            }

            t++;
        }

        cells = petriDish.evolve().toArray(new Integer[0]);
        //delay(50);

    }

}
