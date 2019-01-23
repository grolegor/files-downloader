package center.orbita.downloader

import center.orbita.downloader.args.AppArgs
import center.orbita.downloader.file.FileDownloader
import center.orbita.downloader.parser.FileListParser
import center.orbita.downloader.utils.fileName
import center.orbita.downloader.utils.hexString
import com.beust.jcommander.JCommander
import mu.KotlinLogging
import java.io.IOException
import java.net.URL
import java.nio.file.Paths

private val logger = KotlinLogging.logger { }

fun main(args: Array<String>) {

    val appArgs = AppArgs()
    val jtc: JCommander = JCommander.newBuilder().addObject(appArgs).build()
    jtc.parse(*args)

    if(appArgs.help){
        jtc.usage()
        return
    }

    val listFilesContent = URL(appArgs.listFilesURL).readText()

    FileListParser(listFilesContent).parse().parallelStream().forEach {
        val file = Paths.get(appArgs.outputDirectory, it.url.fileName()).toFile()
        val fileDownloader = FileDownloader(url = it.url, outputFile = file)
        try {
            val expectedMD5 = fileDownloader.download().digest().hexString()
            println("${file.name},expected md5: $expectedMD5, received md5: ${it.checkSum}")
        } catch (e: IOException) {
            file.deleteOnExit()
            logger.error { "can not download file from ${it.url}: $e" }
        }
    }
}
