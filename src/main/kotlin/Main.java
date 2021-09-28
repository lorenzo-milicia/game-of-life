import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet processing;

    public static final int cellSize = 10; //to avoid bugs make sure the resolution is a multiple of this
    public static final Resolution resolution = new Resolution(1000, 1000);
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

        Matrix<Integer> matrix = GetMatrix.INSTANCE.getMatrix(grid.x, grid.y);

        petriDish = PetriBuilder.builder(
                petriBuilder -> {
                    petriBuilder.setColumns(grid.x);
                    petriBuilder.setRows(grid.y);
                    petriBuilder.setIntList(matrix.toList());
                    /*petriBuilder.setPopulationStrategy(
                            cell -> {
                                if (round((float) Math.random()) == 1) {
                                    cell.resuscitate();
                                }
                                return null;
                            }
                    );*/
                    return null;
                }
        );

    }

    public void draw() {
        background(0);

        Display.display(petriDish);

        if (isEvolutionHappening && frameCount % 10 == 0) {
            petriDish.evolve();
        }

        t++;
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
