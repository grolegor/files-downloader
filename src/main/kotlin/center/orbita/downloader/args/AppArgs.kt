package center.orbita.downloader.args

import com.beust.jcommander.Parameter

class AppArgs {

    @Parameter(names = ["--url"], required = true,
            description = "URL of the file that contains comma-separated list of links to download and its md5 checksum")
    lateinit var listFilesURL: String
        private set

    @Parameter(names = ["-d", "--outputDirectory"], validateWith = [WriteAccessToDirectoryValidator::class],
            description = "Absolute or relative path to the directory where all files will be downloaded to")
    var outputDirectory: String = ""
        private set

    @Parameter(names = ["--help"], help = true,
            description = "Print the help")
    var help = false
        private set
}
