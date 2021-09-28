object GetMatrix {

	fun getMatrix(n: Int, m: Int): Matrix<Int> {

		return buildMatrix<Int>(n, m) {
			all(0)
			gospersCannon(20, 20, 1, 0)
			//spaceship(50, 50, 1, 0, horizontalFlip = false, verticalFlip = true)
			gospersCannon(80, 20, 1, 0, horizontalFlip = false, verticalFlip = true)
		}
	}

}