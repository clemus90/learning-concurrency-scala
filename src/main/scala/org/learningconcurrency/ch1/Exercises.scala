package org.learningconcurrency.ch1

object Exercises {
    def check[T](xs: Seq[T])(pred: T => Boolean): Boolean =
    try {
        xs.foldLeft(true)( (acc,item) => acc && pred(item))
    } catch {
        case e: Exception => false
    }

    def compose[A, B, C] (g: B => C, f: A => B): A => C = (a: A) => g(f(a))

    def fuse[A, B](a: Option[A], b: Option[B]): Option[(A,B)] = {
        for (
            aContent <- a;
            bContent <- b)
        yield (aContent, bContent)
    }

    case class Pair[P, Q](first: P, second: Q)
}