package domain

enum class RelativeCoordinates(val offset: Offset) {
	//@formatter:off
	TOP_LEFT        (Offset(-1, -1)),
	TOP_CENTER      (Offset(-1, +0)),
	TOP_RIGHT       (Offset(-1, +1)),
	LEFT            (Offset(+0, -1)),
	RIGHT           (Offset(+0, +1)),
	BOTTOM_LEFT     (Offset(+1, -1)),
	BOTTOM_CENTER   (Offset(+1, +0)),
	BOTTOM_RIGHT    (Offset(+1, +1)),
	//@formatter:on
}

class Offset(
	val vertical: Int,
	val horizontal: Int
)