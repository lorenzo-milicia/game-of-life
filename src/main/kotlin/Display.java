public class Display {
    static boolean rotation = true;
    public static void display(PetriDish petriDish) {
        for (int i = 0; i < petriDish.getNumberOfCells(); i++) {
            int column = i % Main.grid.x;
            int row = i / Main.grid.x;
            if (petriDish.getCells().get(i).isAlive()) {
//                Main.processing.fill((column + Main.t) % 255, 255, 255 * petriDish.getCells().get(i).getCellAliveCounter() / 50.f);
                Main.processing.fill((column + Main.t) % 255, 255, 255);

                Main.processing.push();
                Main.processing.translate(column * Main.cellSize + Main.cellSize/2, row * Main.cellSize + Main.cellSize/2);
                if (rotation) {
                    Main.processing.rotate(Main.t / 20.f);
                }
                Main.processing.square(- Main.cellSize/2, - Main.cellSize/2, Main.cellSize);
                Main.processing.pop();

            }
        }
    }
}
