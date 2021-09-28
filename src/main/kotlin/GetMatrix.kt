object GetMatrix {

	fun getMatrix(n: Int, m: Int): Matrix<Int> {

		return buildMatrix(n, m) {
			all(0)
			spaceship(10, 10, 1, 0)

		}
	}

}