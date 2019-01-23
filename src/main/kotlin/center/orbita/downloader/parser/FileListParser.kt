package center.orbita.downloader.parser

import java.net.URL

class FileListParser(private val content: String) {

    fun parse(): List<Row> {
        //todo validate rows
        return content.lines().map { line -> line.split(",") }.filter { it.size == 2 }.map { Row(URL(it[0].trim()), it[1].trim()) }
    }

    class Row(val url: URL, val checkSum: String)
}