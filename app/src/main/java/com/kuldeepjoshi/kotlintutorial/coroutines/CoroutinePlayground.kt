package com.kuldeepjoshi.kotlintutorial.coroutines

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//Coroutines in Kotlin
//
//Creating and using threads for background tasks directly has its place in Android, but Kotlin also
//offers Coroutines which provide a more flexible and easier way to manage concurrency.
//
//Coroutines enable multitasking, but provide another level of abstraction over simply working with
//threads. One key feature of coroutines is the ability to store state, so that they can be halted
//and resumed. A coroutine may or may not execute.
//
//The state, represented by continuations, allows portions of code to signal when they need to
//hand over control or wait for another coroutine to complete its work before resuming. This flow
//is called cooperative multitasking. Kotlin's implementation of coroutines adds a number of features
//to assist multitasking. In addition to continuations, creating a coroutine encompasses that work
//in a Job, a cancelable unit of work with a lifecycle, inside a CoroutineScope. A CoroutineScope is
//a context that enforces cancellation and other rules to its children and their children recursively.
//A Dispatcher manages which backing thread the coroutine will use for its execution, removing the
//responsibility of when and where to use a new thread from the developer.
//
//========================================
//Job >> A cancelable unit of work, such as one created with the launch() function.
//========================================
//CoroutineScope >> Functions used to create new coroutines such as launch() and async() extend CoroutineScope.
//========================================
//Dispatcher >> Determines the thread the coroutine will use. The Main dispatcher will always run
//coroutines onthe main thread, while dispatchers like Default, IO, or Unconfined will use other threads.
//========================================

fun main() {

    //OUTPUT:
    //    Hello from Thread[DefaultDispatcher-worker-3,5,main]`
    //    Hello from Thread[DefaultDispatcher-worker-4,5,main]
    //    Hello from Thread[DefaultDispatcher-worker-1,5,main]`
    repeat(3) {
        GlobalScope.launch {
            println("Hello from ${Thread.currentThread()}")
        }
    }

    //Below runBlocking function does not have async therefore, it will execute synchronously.
    //OUTPUT:
    //  Entering getValue() at 21:11:30.343261
    //  Leaving getValue() at 21:11:30.652129
    //  Entering getValue() at 21:11:30.653338
    //  Leaving getValue() at 21:11:30.957487
    //  W/O Async | Result of Number 1 is >> 0.7679579154822086
    //  W/O Async | Result of Number 2 is >> 0.6297268561385565
    //  W/O Async | result of num1 + num2 is 1.397684771620765
    runBlocking {
        val number = getValue()
        val numberTwo = getValue()
        println("W/O Async | Result of Number 1 is >> $number")
        println("W/O Async | Result of Number 2 is >> $numberTwo")
        println("W/O Async | result of num1 + num2 is ${number + numberTwo}")
    }


    //Below runBlocking function does have "async" therefore, it will execute Asynchronously.
    //await() is used to execute output when async method return some result.
    //OUTPUT:
    //    With Async | Result of Number 1 is >> DeferredCoroutine{Active}@42eca56e
    //    With Async | Result of Number 2 is >> DeferredCoroutine{Active}@52f759d7
    //    Entering getValue() at 21:11:30.968963
    //    Entering getValue() at 21:11:30.969126
    //    Leaving getValue() at 21:11:31.274134
    //    Leaving getValue() at 21:11:31.275694
    //    With Async | result of num1 + num2 is 1.7489290415408412
    runBlocking {
        val number = async { getValue() }
        val numberTwo = async { getValue() }
        println("With Async | Result of Number 1 is >> $number")
        println("With Async | Result of Number 2 is >> $numberTwo")
        println("With Async | result of num1 + num2 is ${number.await() + numberTwo.await()}")

    }

    runBlocking {
        practiceCoroutines()
    }

}

@RequiresApi(Build.VERSION_CODES.O)
val formatter = DateTimeFormatter.ISO_LOCAL_TIME!!

@RequiresApi(Build.VERSION_CODES.O)
val time = { formatter.format(LocalDateTime.now()) }

//==================================
//When to mark functions as suspend
//==================================

//In the previous example, you may have noticed that the getValue() function is also defined with
//the suspend keyword. The reason is that it calls delay(), which is also a suspend function.
//Whenever a function calls another suspend function, then it should also be a suspend function.

//If this is the case, then why wouldn't the main() function in our example be marked with suspend?
//It does call getValue(), after all.

//Not necessarily. getValue() is actually called in the function passed into runBlocking(),
//which is a suspend function, similar to the ones passed into launch() and async().
//However, getValue() is not called in main() itself, nor is runBlocking() a suspend function,
//so main() is not marked with suspend. If a function does not call a suspend function,
//then it does not need to be a suspend function itself.

suspend fun getValue(): Double {

    println("Entering getValue() at ${time()}")
    delay(300)
    println("Leaving getValue() at ${time()}")

    return Math.random()
}


//==================================
//Practice
//==================================

suspend fun practiceCoroutines(){

    val states = arrayOf("Starting", "Doing Task 1" , "Doing Task 2" ,"Ending")

    repeat(3){

        GlobalScope.launch {
            println("${Thread.currentThread()} has started")
            for (i in states) {
                println("${Thread.currentThread()} - $i")
                delay(5000)
            }
        }

    }

}