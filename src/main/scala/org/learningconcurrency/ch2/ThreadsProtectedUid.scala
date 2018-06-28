package org.learningconcurrency.ch2

import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

object ThreadsProtectedUid extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log
    
    var uidCount = 0L
    def getUniqueId() = this.synchronized {
        val freshUid = uidCount + 1
        uidCount = freshUid
        freshUid
    }

    def printUniqueIds(n: Int): Unit = {
        val uids = for (i <- 0 until n) yield getUniqueId()
        log(s"Generated uids: $uids")
    }

    val t = thread { printUniqueIds(5) }
    printUniqueIds(5)
    t.join()
}