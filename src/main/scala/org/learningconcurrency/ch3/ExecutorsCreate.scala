package org.learningconcurrency.ch3

import scala.concurrent._
import java.util.concurrent.ForkJoinPool
import org.learningconcurrency.Logger.l

object ExecutorsCreate extends App {
    val executor = new ForkJoinPool
    executor.execute(new Runnable {
        def run() = l.log("This task is run asynchronously.")
    })
    Thread.sleep(500)
}