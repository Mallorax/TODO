package pl.patrykzygo.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class Task (

    @PrimaryKey(autoGenerate = true)
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
