package pl.patrykzygo.todo.Services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.util.Log
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver (){

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show()
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val mp = MediaPlayer.create(context, uri)
        mp.start()
    }
}