package ru.vsu.grammar.converter.api

import java.util.*

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */
data class Grammar(val startRule: String,
                   val terminals: Set<String>,
                   val nonTerminals: Set<String>,
                   private val rulesSet: Set<Rule>) {

    var rules: Map<String, Set<Rule>>
        private set

    init {
        val map = HashMap<String, HashSet<Rule>>()
        rulesSet.forEach { rule ->
            val set = map.computeIfAbsent(rule.source) { key -> HashSet<Rule>() }
            set += rule
        }
        rules = map
    }

}