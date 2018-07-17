package org.learningconcurrency.ch3

import org.learningconcurrency.ThreadUtils.thread
import org.learningconcurrency.Logger.l

object LazyValsAndBlocking extends App {
    lazy val x: Int = {
        val t = thread { l.log(s"Initializing $x.") }
        t.join()
        1
    }
    x
}