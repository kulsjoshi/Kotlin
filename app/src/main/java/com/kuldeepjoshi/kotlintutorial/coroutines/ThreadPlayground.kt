package com.kuldeepjoshi.kotlintutorial.coroutines

fun main() {

    val thread = Thread {
        println("${Thread.currentThread()} has run")
        println("===================")
    }

    thread.start()

    val states = arrayOf("Starting", "Doing Task - 1", "Doing Task - 2", "Ending")

    repeat(3) {
        Thread {
            println("${Thread.currentThread()} has started")
            for (i in states) {
                println("${Thread.currentThread()} - $i")
                Thread.sleep(50)
            }
            println("===================")
        }.start()
    }

    var count = 0
    for(i in 1..50){
        Thread{
            count += 1
            println("Thread: $i and Count: $count")
        }.start()
    }


}