package org.learningconcurrency.ch3

import scala.concurrent._
import java.util.concurrent.{ForkJoinPool, TimeUnit}
import org.learningconcurrency.Logger.l

object ExecutorsForkJoinNoSleep extends App {
    val executor = new ForkJoinPool
    executor.execute(new Runnable {
        def run() = l.log("This task is run asynchronously.")
    })
    executor.shutdown()
    executor.awaitTermination(60, TimeUnit.SECONDS)
}