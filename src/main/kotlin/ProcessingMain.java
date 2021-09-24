import processing.core.PApplet;

import java.util.Arrays;


public class ProcessingMain extends PApplet {

    private static final int dimension = 100;
    float t = 0;
    Integer[] cells = new Integer[dimension * dimension];
    public static void main(String[] args) {
        PApplet.main("ProcessingMain");
    }

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
        background(0);
        colorMode(HSB, 255);

        for (int i = 0; i < cells.length; i++) {
            cells[i] = round(pow(random(1), 6));
        }
//        Evolver.INSTANCE.evolveKt(Arrays.asList(cells), 10);
    }

    public void draw() {
        background(0);

        for (int i = 0; i < cells.length; i++) {
            int column = i % dimension;
            int row = i / dimension;
            int size = 1000 / dimension;
            if (cells[i] == 1) {
                fill((column + t/1000.f) % 255, 255,255);
                //ellipse(column * size, row * size, size, size);
                rect(column * size, row * size, size, size);
            } else {
                fill(0);
            }

            t++;
        }

        cells = Evolver.INSTANCE.evolve(Arrays.asList(cells), dimension).toArray(new Integer[0]);
        delay(50);

    }

}
