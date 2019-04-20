package utils

import data.document.InlineElement

object CoordinateUtils {
    fun <E : InlineElement<E>> fillNeighbors(index: Int, element: E, elements: List<E>) {
        element.neighbor.previous = if (index > 0) elements[index - 1] else null
        element.neighbor.next = if (index < elements.lastIndex) elements[index + 1] else null
    }
}