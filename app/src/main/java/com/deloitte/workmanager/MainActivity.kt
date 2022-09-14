package com.deloitte.workmanager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
  @RequiresApi(Build.VERSION_CODES.M)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val myConstraints = Constraints.Builder()
      .setRequiresDeviceIdle(true) //checks whether device should be idle for the WorkRequest to run
      .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
      .setRequiredNetworkType(NetworkType.CONNECTED) //checks whether device should have Network Connection
      .setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
      .setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
      .build()

    val data = Data.Builder().putString("DATA","START").build()

    val yourWorkRequest = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInputData(data).setInitialDelay(10,TimeUnit.SECONDS).build()
   WorkManager.getInstance(this).enqueue(yourWorkRequest)
    val yourWorkRequest1 = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS).build()
    val yourWorkRequest2 = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS).build()
    val yourWorkRequest3 = OneTimeWorkRequest.Builder(MainWorkerClass::class.java).setInitialDelay(10,TimeUnit.SECONDS).build()

   /* val yourWorkRequest =  PeriodicWorkRequest.Builder(MainWorkerClass::class.java,16,TimeUnit.MINUTES).build()
      WorkManager.getInstance(this).enqueueUniquePeriodicWork(TAG,ExistingPeriodicWorkPolicy.KEEP,yourWorkRequest)
*/
   /* WorkManager.getInstance(this).getWorkInfoByIdLiveData(yourWorkRequest.id)
      .observe(this, Observer { workInfo ->
        if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
          Log.v(TAG,"succeeded")
        }
      })*/

    //WorkManager.getInstance(this).beginWith(yourWorkRequest).then(yourWorkRequest1).enqueue()

    /*WorkManager.getInstance(this)
      .beginWith(listOf(yourWorkRequest, yourWorkRequest1))
      .then(yourWorkRequest2)
      .then(yourWorkRequest3)
      .enqueue()*/
  }
}