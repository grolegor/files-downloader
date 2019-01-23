package center.orbita.downloader.file

import center.orbita.downloader.utils.hexString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class FileDownloaderTest {

    private val outputFile = createTempFile()

    @Test
    fun downloadEmptyFile() {
        val emptyFileURL = this.javaClass.getResource("/emptyFile")
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", FileDownloader(emptyFileURL, outputFile).download().digest().hexString())
        compareFiles(File(emptyFileURL.toURI()), outputFile)
    }

    @Test
    fun downloadTestFile() {
        val testFileURL = this.javaClass.getResource("/testFile")
        assertEquals("098f6bcd4621d373cade4e832627b4f6", FileDownloader(testFileURL, outputFile).download().digest().hexString())
        compareFiles(File(testFileURL.toURI()), outputFile)
    }

    private fun compareFiles(urlFile: File, downloadedFile: File) {
        Assertions.assertArrayEquals(getContentFile(urlFile), getContentFile(downloadedFile))
    }

    private fun getContentFile(file: File): Array<ByteArray> = arrayOf(Files.readAllBytes(Paths.get(file.toURI())))

    @AfterEach
    fun before() {
        outputFile.deleteOnExit()
    }
}