package pl.patrykzygo.todo.domain


import pl.patrykzygo.todo.database.TaskEntity
import java.text.SimpleDateFormat
import java.util.*


data class Task(

    val taskId: Long = 0L,

    var name: String,

    var description: String,

    var date: Calendar?,

    var hasNotification: Boolean,

    var notificationType: String,

    var priority: Int,

    var tag: String

){
    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is Task){
            false
        }else{
            this.name == other.name &&
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
        this.name,
        this.description,
        let {
            if(it.date != null) {
                val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
                format.format(date?.time)
            }else{
                ""
            }
        },
        this.hasNotification,
        this.notificationType,
        this.priority,
        this.tag
    )
}

