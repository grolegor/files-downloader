package center.orbita.downloader.file

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import java.net.URLConnection
import java.security.MessageDigest

class FileDownloader(private val url: URL, private val outputFile: File, checkSumAlgorithm: String = "MD5") {

    private val messageDigest: MessageDigest = MessageDigest.getInstance(checkSumAlgorithm)

    private fun copyToOutputStreamAndCalculateMessageDigest(inputStream: InputStream, outputStream: OutputStream) {
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        var bytes = inputStream.read(buffer)
        while (bytes >= 0) {
            outputStream.write(buffer, 0, bytes)
            messageDigest.update(buffer, 0, bytes)
            bytes = inputStream.read(buffer)
        }
    }

    fun download(): MessageDigest {
        FileOutputStream(outputFile).use { outputStream ->
            val connection: URLConnection = url.openConnection()
            connection.getInputStream().use { inputStream ->
                copyToOutputStreamAndCalculateMessageDigest(inputStream, outputStream)
            }
        }
        return messageDigest
    }
}