package center.orbita.downloader.parser

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.net.URL

internal class FileListParserTest {

    @Test
    fun emptyContentFile() {
        val emptyContent = ""
        assertArrayEquals(listOf<FileListParser.Row>().toTypedArray(),
                FileListParser(emptyContent).parse().toTypedArray())
    }

    @Test
    fun invalidURLContentFile() {
        val emptyContent = """
            wrongURL,098f6bcd4621d373cade4e832627b4f6
        """.trimIndent()
        assertArrayEquals(listOf<FileListParser.Row>().toTypedArray(),
                FileListParser(emptyContent).parse().toTypedArray())
    }

    @Test
    fun invalidMD5ContentFile() {
        val emptyContent = """
            http://orbita/test.zip,098f6bcd4621d373cade4e832627b4f6
        """.trimIndent()
        assertArrayEquals(listOf(FileListParser.Row(URL("http://orbita/test.zip"), "098f6bcd4621d373cade4e832627b4f6")).toTypedArray(),
                FileListParser(emptyContent).parse().toTypedArray())
    }

    @Test
    fun parse() {
        val content = """
            http://evviva-adk.info/v/file1.7z,28fd486ed08c40d75bcdd6b749348692
            http://evviva-adk.info/v/file2.7z,9b6d12dde0d6c8f30236b58765358ofa
            http://evviva-adk.info/v/11.7z,cd9be49068112e2e6b128212680d3aab
            http://evviva-adk.info/v/file3.7z,91f8e19c4921b2800407e0b9595dc72e
            http://evviva-adk.info/v/file3.1.7z,5dc371c23b76e823d9d2df48c116f007
            http://evviva-adk.info/v/file4.7z,ce26dc89a1eb81afa5b783e379c46a41
            http://evviva-adk.info/v/file5.7z,33e65fdfd0670bf73c13fd87db62f41a
        """.trimIndent()

        val expectedList = listOf(
                FileListParser.Row(URL("http://evviva-adk.info/v/file1.7z"), "28fd486ed08c40d75bcdd6b749348692"),
                FileListParser.Row(URL("http://evviva-adk.info/v/file2.7z"), "9b6d12dde0d6c8f30236b58765358ofa"),
                FileListParser.Row(URL("http://evviva-adk.info/v/11.7z"), "cd9be49068112e2e6b128212680d3aab"),
                FileListParser.Row(URL("http://evviva-adk.info/v/file3.7z"), "91f8e19c4921b2800407e0b9595dc72e"),
                FileListParser.Row(URL("http://evviva-adk.info/v/file3.1.7z"), "5dc371c23b76e823d9d2df48c116f007"),
                FileListParser.Row(URL("http://evviva-adk.info/v/file4.7z"), "ce26dc89a1eb81afa5b783e379c46a41"),
                FileListParser.Row(URL("http://evviva-adk.info/v/file5.7z"), "33e65fdfd0670bf73c13fd87db62f41a")

        )

        assertArrayEquals(expectedList.toTypedArray(), FileListParser(content).parse().toTypedArray())
    }

}