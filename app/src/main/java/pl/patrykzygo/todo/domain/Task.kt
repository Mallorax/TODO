package pl.patrykzygo.todo.domain


import pl.patrykzygo.todo.database.TaskEntity
import java.text.DateFormat
import java.util.*


data class Task(

    val taskId: Long = 0L,

    var title: String,

    var description: String,

    var date: Date,

    var hasNotification: Boolean,

    var notificationType: String,

    var priority: Int,

    var tag: String

)

fun Task.toDatabaseEntity():TaskEntity{
    return TaskEntity(
        this.taskId,
        this.title,
        this.description,
        DateFormat.getInstance().format(date),
        this.hasNotification,
        this.notificationType,
        this.priority,
        this.tag
    )
}

