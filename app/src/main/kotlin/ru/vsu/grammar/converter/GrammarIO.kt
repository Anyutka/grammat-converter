package ru.vsu.grammar.converter

import mu.KLogger
import mu.KLogging
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import ru.vsu.grammar.converter.api.Grammar
import ru.vsu.grammar.converter.api.Rule
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.util.*

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */
class GrammarIO {

    companion object: KLogging()

    fun read(input: InputStream): Grammar {
        val parser = JSONParser()
        val root = parser.parse(InputStreamReader(input)) as JSONObject
        val grammar = processJSON(root)
        logger.info { "Grammar reading complete. Result: $grammar" }
        return grammar
    }

    fun write(grammar: Grammar, output: OutputStream) {
        throw NotImplementedError()
    }

    private fun processJSON(root: JSONObject): Grammar {
        val terminals = HashSet<String>()
        val terminalsData = root["terminals"] as JSONArray
        terminalsData.forEach { terminal -> terminals += terminal as String }
        logger.debug { "terminals $terminals" }

        val nonTerminals = HashSet<String>()
        val nonTerminalsData = root["nonTerminals"] as JSONArray
        nonTerminalsData.forEach { nonTerminal -> nonTerminals += nonTerminal as String }
        logger.debug { "non terminals $nonTerminals" }

        val start = root["startRule"] as String
        if (!nonTerminals.contains(start))
            throw IllegalArgumentException("Rule [$start] does not presented in non terminals data")
        logger.debug { "start rule $start" }

        val rules = HashSet<Rule>()
        val rulesData = root["rules"] as JSONArray
        rulesData.map { it as JSONObject } .forEach { rule ->
            val from = rule["from"] as String
            if (!nonTerminals.contains(from))
                throw IllegalArgumentException("Rule [$from] does not presented in non terminals data")

            val targets = HashSet<List<String>>()
            val to = rule["to"] as JSONArray
            to.map { it as JSONArray } .forEach { targetsData ->
                val list = ArrayList<String>()
                targetsData.map { it as String }.forEach { target ->
                    if (!nonTerminals.contains(target) && !terminals.contains(target))
                        throw IllegalArgumentException("Rule [$target] does not presented in non data")
                    list += target
                }
                targets += list
            }

            rules += Rule(from, targets)
        }
        logger.debug { "rules $rules" }

        return Grammar(start, terminals, nonTerminals, rules)
    }
}