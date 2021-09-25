import processing.core.PApplet;

import java.util.Arrays;


public class ProcessingMain extends PApplet {
    public static PApplet processing;

    public static final Resolution resolution = new Resolution(1920, 1080);
    public static final int cellSize = 40; //to avoid bugs make sure the resolution is a multiple of this
    public static final Grid grid = new Grid(resolution.horizontal / cellSize, resolution.vertical / cellSize);
    Integer[] initialState;
    PetriDish petriDish;
    public static float t = 0;
    private boolean isEvolutionHappening = true;

    public static void main(String[] args) {
        PApplet.main("ProcessingMain");
    }

    public void settings() {
        //fullScreen();
        size(resolution.horizontal, resolution.vertical, JAVA2D);

    }

    public void setup() {
        processing = this;

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

        Display.display(petriDish);

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
