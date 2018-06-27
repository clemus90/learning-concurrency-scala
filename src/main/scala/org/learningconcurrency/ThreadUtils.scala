package org.learningconcurrency

package object ThreadUtils {
    def thread(body: =>Unit): Thread = {
        val t = new Thread {
            override def run() = body
        }
        t.start()
        t
    }
}