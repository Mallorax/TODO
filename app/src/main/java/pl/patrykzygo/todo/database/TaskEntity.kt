package pl.patrykzygo.todo.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.patrykzygo.todo.domain.Task

@Entity(tableName = "tasks_table")
data class TaskEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    val taskId: Long,

    @ColumnInfo(name = "task_title")
    var title: String,

    @ColumnInfo(name = "task_description")
    var description: String,

    @Embedded
    var date: TimestampDbModel?,

    @ColumnInfo(name = "task_has_notification")
    var hasNotification: Boolean,

    @ColumnInfo(name = "task_notification_type")
    var notificationType: String,

    @ColumnInfo(name = "task_priority")
    var priority: Int,

    @ColumnInfo(name = "task_tag")
    var tag: String
) {


    fun toDomainModel(): Task {
        var task = Task(
            this.title,
            this.description,
            this.date?.toDomainModel(),
            this.hasNotification,
            this.notificationType,
            this.priority,
            this.tag
        )
        task.taskId = this.taskId
        return task
    }

}
