package pl.patrykzygo.todo.database

class NotificationType(val type: String) {

    companion object {

        const val NOTIFICATION_ALARM = "Alarm"
        const val NOTIFICATION_POPUP = "Popup"
        const val NOTIFICATION_NONE = "None"
    }
}