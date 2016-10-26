package ru.vsu.grammar.converter

import mu.KLogging

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */

val logger = KLogging().logger("ru.vsu.grammar.converter.main")

fun main(args: Array<String>) {
    logger.info("Starting ...")
    val core = ConverterCore()
    logger.info("Complete")
}