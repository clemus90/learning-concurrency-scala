package org.learningconcurrency.ch3

import java.util.concurrent.atomic.AtomicLong
import org.learningconcurrency.ExecutionContextUtils.execute
import org.learningconcurrency.Logger.l

object AtomicUid extends App {
    private val uid = new AtomicLong(0L)

    def getUniqueId(): Long = uid.incrementAndGet()

    import scala.annotation.tailrec 
    @tailrec def getUniqueIdCas(): Long = {
        val oldUid = uid.get
        val newUid = oldUid + 1
        if(uid.compareAndSet(oldUid, newUid)) newUid
        else getUniqueIdCas()
    }
    execute { l.log(s"Uid asynchronously: ${getUniqueId()}")}
    l.log(s"Got a unique id: ${getUniqueId()}")

    execute { l.log(s"Uid CAS asynchronously: ${getUniqueIdCas()}")}
    l.log(s"Got a unique id CAS: ${getUniqueIdCas()}")
}