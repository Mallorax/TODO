package pl.patrykzygo.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.patrykzygo.todo.domain.Task
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "tasks_table")
data class TaskEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val taskId: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "has_notification")
    var hasNotification: Boolean,

    @ColumnInfo(name = "notification_type")
    var notificationType: String,

    @ColumnInfo(name = "priority")
    var priority: Int,

    @ColumnInfo(name = "tag")
    var tag: String
)


//TODO: Seems like during conversion the info about whether the field in calendar was set or not gets lost have to fix that
fun TaskEntity.asDomainModel(): Task{
    return Task(
        this.taskId,
        this.title,
        this.description,
        let { databaseModel ->
            if(databaseModel.date == ""){
                null
            }else {
                Calendar.getInstance().also {calendar ->
                    calendar.time = (SimpleDateFormat("dd/MM/yyyy HH:mm").parse(this.date))
                }
            }
        },
        this.hasNotification,
        this.notificationType,
        this.priority,
        this.tag
    )
}


