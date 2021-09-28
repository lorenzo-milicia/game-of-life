class PetriBuilder {

	var columns: Int = 0;
	var rows: Int = 0;
	var populationStrategy: Cell.() -> Unit = {}

	fun build(): PetriDish {
		val petri = PetriDish(columns, rows)

		petri.cells.forEach { populationStrategy(it); it.executeFate() }
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

