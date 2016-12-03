package ru.vsu.grammar.converter.alg4

import mu.KLogging
import ru.vsu.grammar.converter.api.Converter
import ru.vsu.grammar.converter.api.EPSILON
import ru.vsu.grammar.converter.api.Grammar
import java.util.*

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
class Algorithm4 {

    companion object : KLogging()

    fun getNonShorterNotTerminals(grammar: Grammar): Set<String> {
        return getDependenciesEpsilonRules(grammar, getEpsilonRules(grammar))
    }

    private fun getEpsilonRules(grammar: Grammar): Set<String> {
        return grammar.rules.filter { rule ->
            rule.value.targets.any {
                it.size == 1 && it.first() == EPSILON
            }
        } .map { it.key } .toSet()
    }

    private fun getDependenciesEpsilonRules(grammar: Grammar, currRules: Set<String>): Set<String> {
        val nextRules = currRules.union(grammar.rules.filter { rule ->
            rule.value.targets.any {target ->
                target.all { currRules.contains(it) }
            }
        } .map { it.key }) .toSet()
        if (nextRules == currRules){
            return nextRules
        }
        logger.debug { "Terminals with eps rules: $nextRules" }
        return getDependenciesEpsilonRules(grammar, nextRules)
    }
}