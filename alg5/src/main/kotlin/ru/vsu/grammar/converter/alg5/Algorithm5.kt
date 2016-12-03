package ru.vsu.grammar.converter.alg5

import mu.KLogging
import ru.vsu.grammar.converter.alg4.Algorithm4
import ru.vsu.grammar.converter.api.Converter
import ru.vsu.grammar.converter.api.EPSILON
import ru.vsu.grammar.converter.api.Grammar
import ru.vsu.grammar.converter.api.Rule
import java.util.*

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
class Algorithm5 : Converter {

    companion object : KLogging()

    override fun convert(grammar: Grammar): Grammar {
        logger.debug { "Perform algorithm 4" }
        val alg4 = Algorithm4()
        val alg4res = alg4.getNonShorterNotTerminals(grammar)
        logger.debug { "Algorithm 4 result: $alg4res" }

        val rulesList = grammar.rules.map{ rule ->
            Rule(rule.key, rule.value.targets.filter {target ->
                !(target.size == 1 && target.first() == EPSILON)
            }.toSet())
        }
        val noEpsilonRules = mutableMapOf(*rulesList.map { Pair(it.source, it) }.toTypedArray())

        val newStartRule = grammar.startRule
        val newNonTerminals = grammar.nonTerminals
        val newRules = generateNewRules(alg4res, noEpsilonRules)
        if (grammar.startRule in alg4res) {
            val newStartRule = grammar.startRule + "'"
            val newNonTerminals = (grammar.nonTerminals as MutableSet<String>).add(newStartRule)
            newRules.put(newStartRule, Rule(newStartRule, setOf(listOf(grammar.startRule))))
        }

        return Grammar(newStartRule, grammar.terminals, newNonTerminals, newRules.values.toSet())
    }

    private fun generateNewRules(alg4res: Set<String>, rules: MutableMap<String, Rule>) : MutableMap<String, Rule> {
        val rulesNew = mutableMapOf<String, Rule>()
        rules.forEach { rule ->
            val targets = mutableSetOf<List<String>>()
            rule.value.targets.forEach { elems ->
                if (!(elems.size == 1 && elems.first() == EPSILON)) {
                    elems.forEach {
                        val newElem = elems.minusElement(it)
                        if (alg4res.contains(it) && newElem.count() > 0) {
                            targets.add(newElem)
                        }
                    }
                    targets.add(elems)
                }
            }
            rulesNew.put(rule.key, Rule(rule.key, targets))
        }
        val beforeCount = rules.flatMap {rule -> rule.value.targets.toList() }.count()
        rules.putAll(rulesNew)
        val afterCount = rules.flatMap {rule -> rule.value.targets.toList() }.count()
        if (beforeCount == afterCount)
            return rules
        return generateNewRules(alg4res, rules)
    }
}