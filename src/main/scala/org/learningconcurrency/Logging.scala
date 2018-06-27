package org.learningconcurrency

trait Logging {
    def log(s: String): Unit
    def warn(s: String) = log("WARN: " + s)
    def error(s: String) = log("ERROR: " + s)
}

class PrintLogging extends Logging {
    def log(s: String) = println(s)
}