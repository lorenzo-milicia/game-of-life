import processing.core.PApplet
import kotlin.random.Random

class Main: PApplet() {

	companion object {

		lateinit var processing: PApplet

		var cellSize: Int = 10 //to avoid bugs make sure the resolution is a multiple of this
		lateinit var resolution: Resolution
		lateinit var grid: Grid

		var t = 0
	}

	private lateinit var petriDish: PetriDish

	private var isEvolutionHappening = true

	override fun settings() {
		//fullScreen(); //remember to comment size() and change the resolution to that of the fullscreen window
		size(resolution.horizontal, resolution.vertical)
	}

	override fun setup() {
		processing = this

		background(0)
		colorMode(HSB, 255f)

		val matrix =
			buildMatrix<Int>(grid.rows, grid.columns) {
				all {
					Random.nextInt(2)
				}
				//gospersCannon(20, 20, 1, 0)
			}

		petriDish = PetriDish.of(matrix)
	}

	override fun draw() {
		background(0)

		Display.display(petriDish)

		if (isEvolutionHappening && frameCount % 5 == 0) {
			petriDish.evolve()
		}

		t++
	}

	override fun keyPressed() {
		if (key == ' ') {
			isEvolutionHappening = !isEvolutionHappening
		}
	}

	override fun mousePressed() {
		val column = mouseX / cellSize
		val row = mouseY / cellSize
		petriDish.switchStateOfCell(row * grid.columns + column)
	}
}

fun main(args: Array<String>) {
	val horizontal = args[0].toInt()
	val vertical = args[1].toInt()
	val cellSize = args[2].toInt()

	Main.cellSize = cellSize
	Main.resolution = Resolution(horizontal, vertical)
	Main.grid = Grid(horizontal / cellSize, vertical / cellSize)
	PApplet.main("Main")
}