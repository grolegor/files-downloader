Files Downloader
============

This is a console application that will:
 - Take as a command line parameter an URL to some file that contains comma-separated list of links to files and its md5 checksum (for instance: http://evviva-adk.info/v/file_list.txt)
 - Read the file by URL
 - Download all files in parallel to some directory and calculate md5 'on the fly'
 - Print file name, calculated checksum and received checksum of every downloaded file

How to run application?
-----------------------------------
There are two ways to execute this application:
 - Using _exec-maven-plugin_:
 
 `mvn compile exec:java -Dexec.args="--url http://evviva-adk.info/v/file_list.txt --outputDirectory /home/user/Downloads"`
 
 - Create an executable jar and run it:
 
 `maven package`
 
 `java -jar files-downloader-1.0.jar --url http://evviva-adk.info/v/file_list.txt --outputDirectory /home/grol/Downloads`