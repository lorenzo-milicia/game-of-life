class PetriBuilder {

	var columns: Int = 0;
	var rows: Int = 0;
	var populationStrategy: Cell.() -> Unit = {}
	var intList: List<Int>? = null

	fun build(): PetriDish {

		val petri = if (intList == null) {
			PetriDish(columns, rows).apply { cells.forEach { populationStrategy(it); it.executeFate() } }
		}
		else PetriDish(columns, rows, intList!!.map { if (it == 1) Cell(CellState.ALIVE) else Cell() })


		return petri
	}


	companion object {

		@JvmStatic
		fun builder(lambda: PetriBuilder.() -> Unit): PetriDish {
			val petridish = PetriBuilder()

			petridish.lambda()

			return petridish.build()
		}
	}
}

