import mu.KLogging
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import ru.vsu.grammar.converter.GrammarIO
import ru.vsu.grammar.converter.api.Grammar
import ru.vsu.grammar.converter.api.Rule
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
@RunWith(JUnitPlatform::class)
class GrammarIOTest : Spek({

    val logger = KLogging().logger

    it("should parse passed file") {
        val io = GrammarIO()
        val grammar = io.read(io.javaClass.getResourceAsStream("/data/simple.json"))
        assertEquals("S", grammar.startRule)
        //TODO: assertions
    }

    it("should be write json") {
        val io = GrammarIO()
        val grammar = Grammar("S", setOf("a", "b", "+"), setOf("S", "T"),
                setOf(
                        Rule("S", setOf(listOf("T"), listOf("T", "+", "S"))),
                        Rule("T", setOf(listOf("a"), listOf("b")))
                ))
        val stream = ByteArrayOutputStream()
        io.write(grammar, stream)
        val string = String(stream.toByteArray())
        logger.info { string }
        assertTrue(string.length > 0)
    }
})