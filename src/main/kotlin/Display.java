public class Display {

    public static void display(PetriDish petriDish) {
        for (int i = 0; i < petriDish.getNumberOfCells(); i++) {
            int column = i % ProcessingMain.grid.x;
            int row = i / ProcessingMain.grid.x;
            if (petriDish.getCells().get(i).isAlive()) {
                ProcessingMain.processing.fill((column + ProcessingMain.t) % 255, 255, 255);
                ProcessingMain.processing.square(column * ProcessingMain.cellSize, row * ProcessingMain.cellSize, ProcessingMain.cellSize);
            } else {
            }
        }
    }
}
