package org.learningconcurrency.ch2

import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

class Page(val txt: String, var position: Int)

object Volatile extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    val pages = for(i <- 1 to 5) yield
        new Page("Na" * (100 - 20 * i) + "Batman!", -1)
    
    @volatile var found = false
    
    for(p <- pages) yield thread {
        var i = 0
        while(i < p.txt.length && !found)
            if(p.txt(i) == '!') {
                p.position = i
                found = true
            } else i += 1
    }
    while(!found) {}
    log(s"results: ${pages.map(_.position)}")
}