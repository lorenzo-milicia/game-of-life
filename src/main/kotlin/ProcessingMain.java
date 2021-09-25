import processing.core.PApplet;

import java.util.Arrays;


public class ProcessingMain extends PApplet {

    private static final Resolution resolution = new Resolution(1920, 1080);
    private static final int cellSize = 40; //to avoid bugs make sure the resolution is a multiple of this
    private static final Grid grid = new Grid(resolution.horizontal / cellSize, resolution.vertical / cellSize);
    Integer[] initialState;
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
        initialState = new Integer[grid.x * grid.y];

        for (int i = 0; i < initialState.length; i++) {
            initialState[i] = round(pow(random(1), 6));
        }

        petriDish = new PetriDish(Arrays.asList(initialState), grid.x, grid.y);
    }

    public void draw() {
        background(0);

        for (int i = 0; i < petriDish.getNumberOfCells(); i++) {
            int column = i % grid.x;
            int row = i / grid.x;
            if (petriDish.getCells().get(i).isAlive()) {
                fill((column + t) % 255, 255, 255);
                square(column * cellSize, row * cellSize, cellSize);
            } else {
                fill(0);
            }
        }

        if (isEvolutionHappening) {
            initialState = petriDish.evolve().toArray(new Integer[0]);
        }
        t++;
        delay(50);
    }

    public void keyPressed() {
        if (key == ' ') {
            isEvolutionHappening = !isEvolutionHappening;
        }
    }

    public void mousePressed() {
        int column = mouseX / cellSize;
        int row = mouseY / cellSize;

        petriDish.switchStateOfCell(row * grid.x + column);
    }

}
