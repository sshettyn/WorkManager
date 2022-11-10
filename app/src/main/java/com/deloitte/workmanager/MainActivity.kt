package com.deloitte.workmanager

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

  // commit test
  // commit test1
  //commit test 2
  //commit test 35
  lateinit var airplaneBroadcastReceiver: AirplaneBroadcastReceiver

  @RequiresApi(Build.VERSION_CODES.M)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    airplaneBroadcastReceiver = AirplaneBroadcastReceiver()


    IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
      registerReceiver(airplaneBroadcastReceiver,it)
    }

    val button = findViewById<Button>(R.id.button)
    button.setOnClickListener {
      AlertDIalogFragment.newInstance().show(supportFragmentManager, null)
    }
  /*  val myConstraints = Constraints.Builder()
      .setRequiresDeviceIdle(false) //checks whether device should be idle for the WorkRequest to run
      .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
      .setRequiresBatteryNotLow(false) // checks whether device battery should have a specific level to run the work request
      .setRequiresStorageNotLow(false) // checks whether device storage should have a specific level to run the work request
      .build()*/

   // val data = Data.Builder().putString("DATA","START").build()

    val yourWorkRequest = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS)/*.setInputData(data)*/.build()
   WorkManager.getInstance(this).enqueue(yourWorkRequest)
    val yourWorkRequest1 = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS).build()
    val yourWorkRequest2 = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS).build()
    val yourWorkRequest3 = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS).build()

    /*val yourWorkRequest =  PeriodicWorkRequest.Builder(MainWorkerClass::class.java,15,TimeUnit.MINUTES).build()
      WorkManager.getInstance(this).enqueueUniquePeriodicWork(TAG,ExistingPeriodicWorkPolicy.KEEP,yourWorkRequest)
*/
   /* WorkManager.getInstance(this).getWorkInfoByIdLiveData(yourWorkRequest.id)
      .observe(this, Observer { workInfo ->
        if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
          Log.v(TAG,"succeeded")
        }
      })*/

    //WorkManager.getInstance(this).beginWith(yourWorkRequest).then(yourWorkRequest1).enqueue()

    WorkManager.getInstance(this)
      .beginWith(listOf(yourWorkRequest, yourWorkRequest1))
      .then(yourWorkRequest2)
      .then(yourWorkRequest3)
      .enqueue()
  }

  override fun onPause() {
    super.onPause()
    Log.v(TAG,"PAUSE")
  }


  override fun onStop() {
    super.onStop()
    unregisterReceiver(airplaneBroadcastReceiver)
    Log.v(TAG,"STOP")
  }



}
