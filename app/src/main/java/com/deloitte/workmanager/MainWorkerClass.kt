package com.deloitte.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "MainWorkerClass"

class MainWorkerClass(appContext: Context, workerParameters: WorkerParameters) :
  Worker(appContext, workerParameters) {

  override fun doWork(): Result {

    val firstValue = inputData.getString("DATA")

    Log.v(TAG,"doWork")
    if (firstValue != null) {
      Log.v(TAG,firstValue)
    }
    return Result.success()
  }
}