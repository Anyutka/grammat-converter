package ru.vsu.grammar.converter

import mu.KLogging
import java.nio.file.Paths

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 26.10.2016.
 */

val logger = KLogging().logger("ru.vsu.grammar.converter.main")

fun main(args: Array<String>) {
    logger.info("Starting ...")

    val name = args[0]
    logger.info { "Read file: $name" }
    val core = ConverterCore()
    val io = GrammarIO()
    val input = io.read(Paths.get(name).toFile().inputStream())
    logger.info { "Grammar read" }

    val result = core.convert(input)
    val resultName = name.dropLast(4) + "res.json"

    logger.info { "Result file: $resultName" }
    val resFile = Paths.get(resultName).toFile()
    resFile.delete()
    resFile.createNewFile()
    io.write(result, resFile.outputStream())
    logger.info("Complete")
}