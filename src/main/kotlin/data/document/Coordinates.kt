package data.document

class Point(var x: Int, var y: Int) {
    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}

class Box(var startPoint: Point, var endPoint: Point) {
    var width = endPoint.x - startPoint.x
    var height = endPoint.y - startPoint.y

    override fun toString(): String {
        return "Box($startPoint, $endPoint)"
    }
}

class Tab(var index: Int, _count: Int) {

    var count: Int = _count
        set(value) {
            field = value
            endIndex = index + value
        }

    var endIndex = index + _count


    override fun toString(): String {
        return "Tab($index, $count)"
    }
}

class Neighbor<T> {
    var previous: T? = null
    var next: T? = null

    fun hasPrevious(): Boolean {
        return previous != null
    }

    fun hasNext(): Boolean {
        return next != null
    }
}

interface InlineElement<T> {
    val neighbor: Neighbor<T>
}