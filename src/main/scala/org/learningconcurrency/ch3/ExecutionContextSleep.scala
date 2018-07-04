package org.learningconcurrency.ch3

import org.learningconcurrency.ExecutionContextUtils.execute
import org.learningconcurrency.Logger.l

object ExecutionContextSleep extends App {
    for(i <- 0 until 32) execute {
        Thread.sleep(2000)
        l.log(s"Task $i completed.")
    }
    Thread.sleep(500)
}