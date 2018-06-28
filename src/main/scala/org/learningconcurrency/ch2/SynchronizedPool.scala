package org.learningconcurrency.ch2

import scala.collection.mutable.Queue
import org.learningconcurrency.PrintLogging

object SynchronizedPool extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    private val tasks = Queue[() => Unit]()
    object Worker extends Thread {
        setDaemon(true)

        def poll(): () => Unit = tasks.synchronized {
            while (tasks.isEmpty) tasks.wait()
            tasks.dequeue()
        }

        override def run() = while (true) {
            val task = poll()
            task()
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

}