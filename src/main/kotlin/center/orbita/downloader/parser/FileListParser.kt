package center.orbita.downloader.parser

import center.orbita.downloader.utils.isValidMD5Hex
import mu.KotlinLogging
import java.net.MalformedURLException
import java.net.URL

private val logger = KotlinLogging.logger { }

class FileListParser(private val content: String) {

    fun parse(): List<Row> {
        val rows: MutableList<Row> = mutableListOf()
        content.lines().map { line -> line.split(",") }.filter { it.size == 2 }.map { Pair(it[0].trim(), it[1].trim()) }.forEach { (urlString, md5) ->
            if (!md5.isValidMD5Hex()) {
                logger.warn { "$md5 is not valid md5 for $urlString" }
            }
            val url: URL
            try {
                url = URL(urlString)
            } catch (e: MalformedURLException) {
                logger.error { "$urlString is not valid URL" }
                return@forEach
            }
            rows += Row(url, md5)
        }

        return rows
    }

    class Row(val url: URL, val checkSum: String)
}