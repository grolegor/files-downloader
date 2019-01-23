package center.orbita.downloader.args

import com.beust.jcommander.Parameter

class AppArgs {

    @Parameter(names = ["--url"], required = true)
    lateinit var listFilesURL: String
        private set

    @Parameter(names = ["-d", "--outputDirectory"])
    var outputDirectory: String = ""
        private set

    @Parameter(names = ["--help"], help = true)
    var help = false
        private set
}
