package pl.patrykzygo.todo.domain


import pl.patrykzygo.todo.database.TaskEntity
import java.util.*


data class Task(
    var name: String,

    var description: String,

    var date: Timestamp?,

    var hasNotification: Boolean,

    var notificationType: String,

    var priority: Int,

    var tag: String

){

    var taskId: Long = System.currentTimeMillis()

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

    fun toDbModel():TaskEntity{
        return TaskEntity(
            this.taskId,
            this.name,
            this.description,
            this.date?.toDbModel(),
            this.hasNotification,
            this.notificationType,
            this.priority,
            this.tag
        )
    }
}



