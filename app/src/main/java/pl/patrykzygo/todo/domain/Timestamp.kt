package pl.patrykzygo.todo.domain

import pl.patrykzygo.todo.database.TimestampDbModel
import java.text.SimpleDateFormat
import java.util.*

data class Timestamp(
    var repetition: String = NONE,
    var timestampDate: Calendar,
    var isDateSet: Boolean = false,
    var isTimeSet: Boolean = false
){

    companion object{
        const val NONE = "none"
        const val DAILY = "everyday"
        const val WEEKLY = "every_week"
        const val MONTHLY = "every_month"
        const val YEARLY = "every_year"
    }

    fun toDbModel():TimestampDbModel{
        return TimestampDbModel(
            this.repetition,
            let{
                val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
                format.format(it.timestampDate.time)
            },
            this.isDateSet,
            this.isTimeSet
        )
    }


}