package org.learningconcurrency.ch3

import scala.concurrent._
import org.learningconcurrency.Logger.l

object ExecutionContextGlobal extends App {
    val ectx = ExecutionContext.global
    ectx.execute(new Runnable {
        def run() = l.log("Running on the execution context.")
    })
    Thread.sleep(500)
}