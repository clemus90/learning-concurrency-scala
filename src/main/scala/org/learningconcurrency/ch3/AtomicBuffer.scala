package org.learningconcurrency.ch3

class AtomicBuffer[T] {
    private val buffer = new AtomicReference[List[T]](Nil)
    @tailrec def +=(x: T): Unit = {
        val xs = buffer.get
        val nxs = x :: xs
        if(!buffer.compareAndSet(xs, nxs)) this += x
    }
}