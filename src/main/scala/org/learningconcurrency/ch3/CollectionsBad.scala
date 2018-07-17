package org.learningconcurrency.ch3

import scala.collection._
import org.learningconcurrency.ExecutionContextUtils.execute
import org.learningconcurrency.Logger.l

object CollectionsBad extends App {
    val buffer = mutable.ArrayBuffer[Int]()
    def asyncAdd(numbers: Seq[Int]) = execute {
        buffer ++= numbers
        l.log(s"buffer = $buffer")
    }
    asyncAdd(0 until 10)
    asyncAdd(10 until 20)
    Thread.sleep(500)

    def betterAsyncAdd(numbers: Seq[Int]) = execute {
        buffer.synchronized {
            buffer ++= numbers
            l.log(s"buffer = $buffer")
        }
    }

}
