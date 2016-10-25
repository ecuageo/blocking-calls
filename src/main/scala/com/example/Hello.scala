package com.example

import java.util.concurrent.{Executors, TimeUnit}

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object Sentinel extends Runnable {
  override def run(): Unit = {
    while (true) {
      println(s"Active threads count: ${Thread.activeCount}")
      Thread.sleep(200)
    }
  }
}

object Hello {
  def main(args: Array[String]): Unit = {
    val futuresCount = 50

    Executors.newSingleThreadScheduledExecutor.schedule(Sentinel, 200, TimeUnit.MILLISECONDS)
    (0 until futuresCount) foreach { _ =>
      Future {
        // blocking {
          println(s"Hello my name is: ${Thread.currentThread.getName}")
          Thread.sleep(5000)
        // }
      }
    }
  }
}  

