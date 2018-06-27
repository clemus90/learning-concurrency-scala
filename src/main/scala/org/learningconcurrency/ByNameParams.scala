package org.learningconcurrency

object ByNameParams extends App {
    def runTwice(body: =>Unit) = {
        body
        body
    }

    runTwice { println("Hello") }
}