package org.learningconcurrency.ch2

import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

object ThreadsSleep extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log
    
    val t = thread {
        Thread.sleep(1000)
        log("New thread running.")
        Thread.sleep(1000)
        log("Still running")
        Thread.sleep(1000)
        log("Completed.")
    }

    t.join()
    log("New Thread joined.")
}