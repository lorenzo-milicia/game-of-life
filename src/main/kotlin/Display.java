public class Display {

    public static void display(PetriDish petriDish) {
        for (int i = 0; i < petriDish.getNumberOfCells(); i++) {
            int column = i % Main.grid.x;
            int row = i / Main.grid.x;
            if (petriDish.getCells().get(i).isAlive()) {
                Main.processing.fill((column + Main.t) % 255, 255, 255);
                Main.processing.square(column * Main.cellSize, row * Main.cellSize, Main.cellSize);
            } else {
            }
        }
    }
}
