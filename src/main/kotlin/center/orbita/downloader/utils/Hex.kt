package center.orbita.downloader.utils

fun ByteArray.hexString() = joinToString("") { String.format("%02x", it) }

fun String.isValidMD5Hex() = matches(Regex("^[a-fA-F0-9]{32}$"))