package pl.patrykzygo.todo.domain

import pl.patrykzygo.todo.database.TimestampDbModel
import java.text.SimpleDateFormat
import java.util.*

data class Timestamp(
    var cycle: String = NONE,
    var timestampDate: Calendar,
    var isDateSet: Boolean = false,
    var isTimeSet: Boolean = false
){

    companion object{
        const val NONE = "None"
        const val DAILY = "Every day"
        const val WEEKLY = "Every week"
        const val MONTHLY = "Every month"
        const val YEARLY = "Every year"
    }

    fun toDbModel():TimestampDbModel{
        return TimestampDbModel(
            this.cycle,
            let{
                val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
                format.format(it.timestampDate.time)
            },
            this.isDateSet,
            this.isTimeSet
        )
    }


}