package ru.vsu.grammar.converter.api

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */
interface Converter {
    fun convert(grammar: Grammar): Grammar
}