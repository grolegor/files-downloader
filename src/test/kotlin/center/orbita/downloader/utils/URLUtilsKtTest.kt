package center.orbita.downloader.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.net.URL

internal class URLUtilsKtTest {

    @Test
    fun fileName() {
        assertEquals("a.zip", URL("http://orbita/a.zip").fileName())
        assertEquals("c", URL("http://orbita/a/b/c").fileName())
        assertEquals("c.txt", URL("http://orbita/a/b/c.txt").fileName())
        assertEquals("c.txt", URL("http://orbita/a/b/c.txt?param1=value").fileName())
    }
}