package analyze.resolver

import data.document.ScannedWord

interface TypeResolver<R> {
    fun resolveWord(word: ScannedWord, lineIterator: MutableListIterator<ScannedWord>): R
}