package org.learningconcurrency.ch3

import java.util.concurrent.atomic.AtomicReference
import org.learningconcurrency.ExecutionContextUtils.execute
import org.learningconcurrency.Logger.l

object ConcurrentFileSystem {
    class Entry (val isDir: Boolean) {
        val state = new AtomicReference[State](new Idle)
    }

    sealed trait State
    class Idle extends State
    class Creating extends State
    class Copying(var n:Int) extends State
    class Deleting extends State

    import scala.annotation.tailrec 
    @tailrec private def prepareForDelete(entry: Entry): Boolean = {
        val s0 = entry.state.get
        s0 match {
            case i: Idle =>
                if (entry.state.compareAndSet(s0, new Deleting)) true
                else prepareForDelete(entry)
            case c: Creating =>
                logMessage("File currently created, cannot delete."); false
            case c: Copying =>
                logMessage("File currently copied, cannot delete."); false
            case d: Deleting =>
                false
        }
    }

    private def logMessage(s: String) = ???

    private def acquire(entry: Entry, state: State) = ???

    private def releaseCopy(e: Entry): Copying = e.state.get match {
        case c: Copying => 
            val nstate = if(c.n == 1) new Idle else new Copying(c.n-1)
            if (e.state.compareAndSet(c, nstate)) c
            else releaseCopy(e)
    }

    private def acquireCopy(e: Entry, c: Copying) = e.state.get match {
        case i: Idle =>
            c.n = 1
            if (!e.state.compareAndSet(i, c)) acquire(e, c)
        case oc: Copying =>
            c.n = oc.n + 1
            if(!e.state.compareAndSet(oc, c)) acquire(e, c)
    }
}