package org.learningconcurrency.ch3

import org.learningconcurrency.ExecutionContextUtils.execute
import org.learningconcurrency.Logger.l

object LazyValsCreate extends App {
    lazy val obj = new AnyRef
    lazy val non = s"made by ${Thread.currentThread.getName}"
    execute {
        l.log(s"EC sees obj = $obj")
        l.log(s"EC sees non = $non")
    }

    l.log(s"Main sees obj = $obj")
    l.log(s"Main sees non = $non")
    Thread.sleep(500)
}