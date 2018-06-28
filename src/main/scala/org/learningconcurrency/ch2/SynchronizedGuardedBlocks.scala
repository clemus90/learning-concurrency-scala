package org.learningconcurrency.ch2

import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

object SynchronizedGuardedBlocks extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log
    
    val lock = new AnyRef
    var message: Option[String] = None
    val greeter = thread {
        lock.synchronized {
            while(message == None) lock.wait()
            log(message.get)
        }
    }
    lock.synchronized {
        message = Some("Hello!")
        lock.notify()
    }

    greeter.join()
}