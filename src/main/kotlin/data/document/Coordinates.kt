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

class Tab(var tabIndex: Int, var tabCount: Int) {
    override fun toString(): String {
        return "Tab($tabIndex, $tabCount)"
    }
}