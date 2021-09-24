import processing.core.PApplet;

public class ProcessingMain extends PApplet {

    private static final int dimension = 50;
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
       // cells = KnownCellMatrices.INSTANCE.oscillator();
    }

    public void draw() {
        background(0);

        for (int i = 0; i < cells.length; i++) {
            Integer column = i % dimension;
            Integer row = i / dimension;
            Integer size = 1000 / dimension;
            if (cells[i] == 1) {
                fill((column + t/1000.f) % 255, 255,255);
                //circle(column * size, row * size, size);
                square(column * size, row * size, size);
            } else {
                fill(0);
            }

            t++;
        }

        cells = Evolver.INSTANCE.evolve(cells, dimension);
        delay(100);

    }

}
