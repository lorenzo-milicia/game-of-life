import processing.core.PApplet;

import java.util.Arrays;


public class Main extends PApplet {
    public static PApplet processing;

    public static final int cellSize = 20; //to avoid bugs make sure the resolution is a multiple of this
    public static final Resolution resolution = new Resolution(1920, 1080);
    public static final Grid grid = new Grid(resolution.horizontal / cellSize, resolution.vertical / cellSize);

    PetriDish petriDish;

    public static float t = 0;
    private boolean isEvolutionHappening = true;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        //fullScreen(JAVA2D); //remember to comment size() and change the resolution to that of the fullscreen window
        size(resolution.horizontal, resolution.vertical, JAVA2D);

    }

    public void setup() {
        processing = this;

        background(0);
        colorMode(HSB, 255);
        Integer[] initialState = new Integer[grid.x * grid.y];

        for (int i = 0; i < initialState.length; i++) {
            initialState[i] = round(pow(random(1), 6));
        }

        petriDish = new PetriDish(Arrays.asList(initialState), grid.x, grid.y);
    }

    public void draw() {
        background(0);

        Display.display(petriDish);

        if (isEvolutionHappening) {
            petriDish.evolve();
            t++;
        }
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