# HTTPLogReader
HTTP Request log reader for internal project


## About

This project parses a HTTP request log file and will output the following information:

* The number of unique IP addresses
* The top 3 most visited URLs
* The top 3 most active IP addresses


The file is expected to be in Common Log Format (CLF).

https://en.wikipedia.org/wiki/Common_Log_Format

This project is using Gradle as its build tool

## Building

The project was build using Gradle.
The Gradle install documentation can be found here: https://gradle.org/install/
Once gradle is installed on your machine, navigate to the project folder and run:

```
gradle run
```

The project is going to output that a filepath was not provided. This referrs to the filepath which this app will ingest.
It can take a relative filepath or a absolute filepath. However, if you are using a relative path, be aware that Gradle will run the app in the /app folder, so the filepath provided may need to be amended.

An example of running then app against a file:

```
gradle run --args=~/TestFile.txt
```


## Versioning


openjdk 22.0.1 2024-04-16

OpenJDK Runtime Environment Homebrew (build 22.0.1)

OpenJDK 64-Bit Server VM Homebrew (build 22.0.1, mixed mode, sharing)

Gradle 8.8

JUnit 4

