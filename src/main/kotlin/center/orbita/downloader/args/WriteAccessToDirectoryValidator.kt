package center.orbita.downloader.args

import com.beust.jcommander.IParameterValidator
import com.beust.jcommander.ParameterException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class WriteAccessToDirectoryValidator : IParameterValidator {
    override fun validate(name: String?, directory: String?) {
        val directoryPath: Path = Paths.get(directory)
        when {
            !Files.isDirectory(directoryPath) -> throw ParameterException("Path $directory is not a directory")
            !Files.isWritable(directoryPath) -> throw ParameterException("You don't have write permissions for $directory")
        }
    }

}