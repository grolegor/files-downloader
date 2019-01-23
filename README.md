Files Downloader
============

This is a console application that can:
 1. Take as a command line parameter an URL to some file that contains comma-separated list of links to files and its md5 checksum (for instance: http://evviva-adk.info/v/file_list.txt)
 2. Read the file by URL
 3. Download all files in parallel to some directory and calculate md5 'on the fly'
 4. Print file name, calculated checksum and received checksum of every downloaded file

How to use?
-----------------------------------
This application has several command line parameters. You can get info about it by `--help`:

    Usage: <main class> [options]
      Options:
        --help
          Print the help
        -d, --outputDirectory
          Absolute or relative path to the directory where all files will be downloaded to
          Default: <empty string>
      * --url
          URL of the file that contains comma-separated list of links to download and its md5 checksum

How to run application?
-----------------------------------
There are two ways to execute this application. For example:
 - Using _exec-maven-plugin_:
 
 `mvn compile exec:java -Dexec.args="--url http://evviva-adk.info/v/file_list.txt --outputDirectory /home/user/Downloads"`
 
 - Create an executable jar and run it. For example:
 
 `maven package`
 
 `java -jar files-downloader-1.0.jar --url http://evviva-adk.info/v/file_list.txt --outputDirectory /home/user/Downloads`