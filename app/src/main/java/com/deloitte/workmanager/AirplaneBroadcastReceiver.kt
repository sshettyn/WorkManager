package com.deloitte.workmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneBroadcastReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context?, intent: Intent?) {
    val state = intent?.getBooleanExtra("state",false)
    if(state == true){
      Toast.makeText(context,"airplane enabled",Toast.LENGTH_LONG).show()
    }else{
      Toast.makeText(context,"airplane not enabled",Toast.LENGTH_LONG).show()

    }
  }
}