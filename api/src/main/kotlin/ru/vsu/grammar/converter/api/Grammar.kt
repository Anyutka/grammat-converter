package ru.vsu.grammar.converter.api

import java.util.*

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */
data class Grammar(val startRule: String,
                   val terminals: Set<String>,
                   val nonTerminals: Set<String>,
                   val rulesSet: Set<Rule>) {

    var rules: Map<String, Rule>
        private set

    init {
        val map = HashMap<String, Rule>()
        rulesSet.forEach { rule ->
            map += Pair(rule.source, rule)
        }
        rules = map
    }

}