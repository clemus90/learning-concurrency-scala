package org.learningconcurrency.ch2

import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

object ThreadsNondeterminism extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    val t = thread { log("New thread running.") }
    log("...")
    log("...")
    t.join()
    log("New Thread joined.")
}