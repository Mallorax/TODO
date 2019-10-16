package pl.patrykzygo.todo.domain


import pl.patrykzygo.todo.database.TaskEntity
import java.text.DateFormat
import java.time.LocalDateTime
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

){
    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is Task){
            false
        }else{
            this.title == other.title &&
                    this.description == other.description &&
                    this.date == other.date &&
                    this.hasNotification == other.hasNotification &&
                    this.notificationType == other.notificationType &&
                    this.priority == other.priority &&
                    this.tag == other.tag
        }
    }
}

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

