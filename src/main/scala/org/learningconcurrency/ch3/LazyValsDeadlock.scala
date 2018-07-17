package org.learningconcurrency.ch3

import org.learningconcurrency.ExecutionContextUtils.execute

object LazyValsDeadlock extends App {

    object A { lazy val x: Int = B.y }
    object B { lazy val y: Int = A.x }
    execute { B.y }
    A.x
}