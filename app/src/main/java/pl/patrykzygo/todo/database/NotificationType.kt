package pl.patrykzygo.todo.database

class NotificationType(val type: String) {

    companion object {

        const val NOTIFICATION_ALARM = "alarm"
        const val NOTIFICATION_POPUP = "popup"
        const val NOTIFICATION_NONE = "none"
    }
}