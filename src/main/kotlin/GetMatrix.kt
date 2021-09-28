object GetMatrix {

	fun getMatrix(n: Int, m: Int): Matrix<Int> {

		return buildMatrix(n, m) {
			all(0)
			gospersCannon(20, 20, 1, 0)
			gospersCannon(50, 10, 1, 0)

		}
	}

}