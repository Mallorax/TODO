package pl.patrykzygo.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import pl.patrykzygo.todo.database.TaskEntity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
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

fun createRandomTasks(count: Int): List<TaskEntity>{
    val tasks = mutableListOf<TaskEntity>()
    for(x in 1..count){
        tasks.add(
            TaskEntity(
                0,
                getRandomString((0..x).random()),
                getRandomString((0..x).random()),
                SimpleDateFormat("dd/MM/yyyy hh:mm").format(randomDate().time),
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

private fun randomDate():Calendar{
    val result = Calendar.getInstance()
    result.set((1900..2000).random(), (1..12).random(), (1..25).random(),(0..23).random(), (0..59).random())
    return result
}