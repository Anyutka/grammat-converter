import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import ru.vsu.grammar.converter.GrammarIO
import kotlin.test.assertEquals

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
@RunWith(JUnitPlatform::class)
class GrammarIOTest : Spek({
    it("should parse passed file") {
        val io = GrammarIO()
        val grammar = io.read(io.javaClass.getResourceAsStream("/data/simple.json"))
        assertEquals("S", grammar.startRule)
        //TODO: assertions
    }
})