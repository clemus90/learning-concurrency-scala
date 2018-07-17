package org.learningconcurrency.ch3

import org.learningconcurrency.ExecutionContextUtils.execute
import org.learningconcurrency.Logger.l

object LazyValsObject extends App {
    object Lazy { l.log("Running Lazy Constructor.") }
    l.log("Main thread is about to reference Lazy.")
    Lazy
    l.log("Main thread completed.")
}