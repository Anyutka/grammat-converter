import org.apache.commons.io.IOUtils
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import ru.vsu.grammar.converter.main
import java.io.File
import java.nio.file.Paths

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 27.10.2016.
 */
@RunWith(JUnitPlatform::class)
class SummaryTest : Spek({
    it("should not failed") {
        val file = Paths.get("./target/data/test.json").toFile()
        file.mkdirs()
        file.delete()
        file.createNewFile()
        IOUtils.copy(javaClass.getResourceAsStream("/data/simple.json"), file.outputStream())
        main(arrayOf("./target/data/test.json"))
    }
})