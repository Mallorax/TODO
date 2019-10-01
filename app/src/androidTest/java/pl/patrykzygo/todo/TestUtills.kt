package pl.patrykzygo.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import pl.patrykzygo.todo.database.Task
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    val observer = Observer<T> { t ->
        value = t
        latch.countDown()
    }

    observeForever(observer)

    latch.await(2, TimeUnit.SECONDS)
    return value
}

fun createRandomTasks(count: Int): List<Task>{
    val tasks = mutableListOf<Task>()
    for(x in 1..count){
        tasks.add(
            Task(
                0,
                getRandomString((0..x).random()),
                getRandomString((0..x).random()),
                getRandomString((0..x).random()),
                Random.nextBoolean(),
                getRandomString((0..x).random()),
                (0..x).random(),
                getRandomString((0..x).random())
            )
        )

    }
    return tasks
}

fun getRandomString(length: Int) : String {
    val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}