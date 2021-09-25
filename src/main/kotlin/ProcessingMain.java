import processing.core.PApplet;

import java.util.Arrays;


public class ProcessingMain extends PApplet {

    private static final Resolution resolution = new Resolution(1920, 1080);
    private static final int cellSize = 20;
    private static final Grid grid = new Grid(resolution.horizontal / cellSize, resolution.vertical / cellSize);
    Integer[] cells;
    PetriDish petriDish;
    float t = 0;
    private boolean isEvolutionHappening = false;

    public static void main(String[] args) {
        PApplet.main("ProcessingMain");
    }

    public void settings() {
        //fullScreen();
        size(resolution.horizontal, resolution.vertical, JAVA2D);

    }

    public void setup() {
        background(0);
        colorMode(HSB, 255);

        cells = new Integer[grid.x * grid.y];

        for (int i = 0; i < cells.length; i++) {
            cells[i] = round(pow(random(1), 6));
        }

        petriDish = new PetriDish(Arrays.asList(cells), grid.x, grid.y);
    }

    public void draw() {
        background(0);

        for (int i = 0; i < cells.length; i++) {
            int column = i % grid.x;
            int row = i / grid.x;
            if (cells[i] == 1) {
                fill((column + t) % 255, 255, 255);
                square(column * cellSize, row * cellSize, cellSize);
            } else {
                fill(0);
            }
        }

        if (isEvolutionHappening) {
            cells = petriDish.evolve().toArray(new Integer[0]);
        }
        t++;
        //delay(50);
    }

    public void keyPressed() {
        if (key == ' ') {
            isEvolutionHappening = !isEvolutionHappening;
        }
    }

}
