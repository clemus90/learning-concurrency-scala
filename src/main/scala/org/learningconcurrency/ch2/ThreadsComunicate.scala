package org.learningconcurrency.ch2

import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

object ThreadsCommunicate extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    var result: String = null
    val t = thread { result = "\nTitle\n" + "=" * 5 }
    t.join()
    log(result)
}