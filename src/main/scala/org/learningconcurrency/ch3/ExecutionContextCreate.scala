package org.learningconcurrency.ch3

import scala.concurrent._
import java.util.concurrent.ForkJoinPool
import org.learningconcurrency.Logger.l

object ExecutionContextCreate extends App {
    val pool = new ForkJoinPool(2)
    val ectx = ExecutionContext.fromExecutorService(pool)
    ectx.execute(new Runnable {
        def run() = l.log("Running on the execution context.")
    })
    Thread.sleep(500)
}