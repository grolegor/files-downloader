package center.orbita.downloader.utils

fun String.isValidMD5Hex() = matches(Regex("^[a-fA-F0-9]{32}$"))