package org.learningconcurrency.ch2

object ThreadsCreation extends App {
    class MyThread extends Thread {
        override def run(): Unit = {
            println("New Thread Running.")
        }
    }
    val t = new MyThread
    t.start()
    t.join()
    println("New Thread Joined.")
}