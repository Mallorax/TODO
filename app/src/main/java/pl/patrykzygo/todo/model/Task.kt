package pl.patrykzygo.todo.model

data class Task (
    var name: String,
    var description: String,
    var date: String,
    var hasNotification: Boolean,
    var notificationType: NotificationType,
    var priority: Int,
    var tag: String)
