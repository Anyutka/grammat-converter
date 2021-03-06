package ru.vsu.grammar.converter

import mu.KLogging
import ru.vsu.grammar.converter.alg4.Algorithm4
import ru.vsu.grammar.converter.alg5.Algorithm5
import ru.vsu.grammar.converter.api.Grammar

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */
class ConverterCore {

    companion object : KLogging()

    fun convert(input: Grammar): Grammar {
        logger.info { "Perform algorithm 5" }
        val alg5 = Algorithm5()
        val alg5res = alg5.convert(input)
        logger.debug { "Algorithm 5 result: $alg5res" }

        return alg5res
    }
}