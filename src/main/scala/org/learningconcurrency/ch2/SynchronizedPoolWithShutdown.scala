package org.learningconcurrency.ch2

import scala.collection.mutable.Queue
import org.learningconcurrency.PrintLogging

object SynchronizedPoolWithShutdown extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    private val tasks = Queue[() => Unit]()
    object Worker extends Thread {

        var terminated = false;

        def poll(): Option[() => Unit] = tasks.synchronized {
            while (tasks.isEmpty && !terminated) tasks.wait()
            if (!terminated) Some(tasks.dequeue()) else None
        }

        import scala.annotation.tailrec
        @tailrec override def run() = poll() match {
            case Some(task) => task(); run()
            case None => 
        }

        def shutdown() = tasks.synchronized {
            terminated = true
            tasks.notify()
        }
    }

    Worker.start()

    def asynchronous(body: =>Unit) = tasks.synchronized {
        tasks.enqueue(() => body)
        tasks.notify()
    }

    asynchronous { log("Hello") }
    asynchronous { log("World!") }
    Thread.sleep(500)
     
    Worker.shutdown()

}