package org.learningconcurrency.ch2

import scala.collection.mutable.ArrayBuffer
import org.learningconcurrency.PrintLogging
import org.learningconcurrency.ThreadUtils.thread

class Account(val name: String, var money: Int)

object SynchronizedNesting extends App {

    val logger = new PrintLogging();
    val log: String => Unit = logger.log

    private val transfers = ArrayBuffer[String]()

    def logTransfer(name: String, n:Int) = transfers.synchronized {
        transfers += s"transfer to account $name = $n"
    }

    def add(account: Account, n: Int) = account.synchronized {
        account.money += n
        if (n>10) logTransfer(account.name, n)
    }

    val victor = new Account("Victor", 100)
    val alejo = new Account("Alejandro", 200)
    val t1 = thread { add(victor, 5) }
    val t2 = thread { add(alejo, 50) }
    val t3 = thread { add(victor, 70) }
    t1.join()
    t2.join()
    t3.join()
    log(s"--- transfers ---\n$transfers")
}