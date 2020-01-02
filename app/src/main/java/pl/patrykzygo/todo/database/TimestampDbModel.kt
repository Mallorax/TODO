package pl.patrykzygo.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.patrykzygo.todo.domain.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class TimestampDbModel(


    @ColumnInfo(name = "timestamp_repetition")
    val repetition: String,

    @ColumnInfo(name = "timestamp_date")
    val date: String,

    @ColumnInfo(name = "timestamp_isDateSet")
    val isDateSet: Boolean,

    @ColumnInfo(name = "timestamp_isTimeSet")
    val isTimeSet: Boolean
) {

    fun toDomainModel(): Timestamp {
        return Timestamp(
            this.repetition,
            let {
                Calendar.getInstance().also { calendar ->
                    calendar.time = (SimpleDateFormat("dd/MM/yyyy HH:mm").parse(this.date))
                }
            },
            this.isDateSet,
            this.isTimeSet
        )
    }
}