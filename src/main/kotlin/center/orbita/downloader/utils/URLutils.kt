package center.orbita.downloader.utils

import java.net.URL
import java.nio.file.Paths

fun URL.fileName(): String = Paths.get(this.path).fileName.toString()