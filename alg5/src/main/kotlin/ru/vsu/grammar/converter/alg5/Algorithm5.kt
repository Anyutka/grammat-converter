package ru.vsu.grammar.converter.alg5

import mu.KLogging
import ru.vsu.grammar.converter.alg4.Algorithm4
import ru.vsu.grammar.converter.api.Converter
import ru.vsu.grammar.converter.api.Grammar

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
class Algorithm5 : Converter {

    companion object : KLogging()

    override fun convert(grammar: Grammar): Grammar {
        logger.debug { "Perform algorithm 4" }
        val alg4 = Algorithm4()
        val alg4res = alg4.convert(grammar)
        logger.debug { "Algorithm 4 result: $alg4res" }
        return alg4res
    }
}