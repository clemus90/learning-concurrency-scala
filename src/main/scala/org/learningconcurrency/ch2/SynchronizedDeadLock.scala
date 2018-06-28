package org.learningconcurrency.ch2

import scala.collection.mutable.ArrayBuffer
import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

object SynchronizedDeadLock extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    def send(a: Account, b: Account, n: Int) = a.synchronized {
        b.synchronized {
            a.money -= n
            b.money += n
        }
    }

    val alex = new Account("Alexander", 1000)
    val farid = new Account("Farid", 2000)
    val t1 = thread { for (i <- 0 until 500) send(alex, farid, 1) }
    val t2 = thread { for (i <- 0 until 500) send(farid, alex, 1) }
    t1.join()
    t2.join()
    log(s"alex = ${alex.money}, farid = ${farid.money}")
}
