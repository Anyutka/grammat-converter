package ru.vsu.grammar.converter.alg4

import ru.vsu.grammar.converter.api.Converter
import ru.vsu.grammar.converter.api.Grammar
import java.util.*

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
class Algorithm4 : Converter {
    override fun convert(grammar: Grammar): Grammar {
        val epsRules = HashSet<String>()
        grammar.rules.forEach { rule ->
            rule.value.targets.forEach {

            }

        }
        return grammar
    }
}