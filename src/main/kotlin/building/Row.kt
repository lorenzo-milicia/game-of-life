package building

class Row<T>(
	private val dimension: Int
) {

	val elements: MutableList<T> = mutableListOf()

	fun getElement(index: Int): T {
		return elements[index]
	}

	fun changeElement(index: Int, new: T) {
		elements[index] = new
	}

	fun addElement(element: T) {
		elements.add(element)
	}

	constructor(dimension: Int, element: T): this(dimension){
		repeat((0 until dimension).count()) { elements.add(element) }
	}

	constructor(dimension: Int, function: () -> T): this(dimension){
		repeat((0 until dimension).count()) { elements.add(function()) }
	}
}