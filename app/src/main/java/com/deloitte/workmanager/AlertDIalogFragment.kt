package com.deloitte.workmanager

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment

class AlertDIalogFragment  : DialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_dialog_with_data, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupClickListeners(view)
  }

  companion object {

    fun newInstance(): AlertDIalogFragment {

      val fragment = AlertDIalogFragment()

      return fragment
    }
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(
      WindowManager.LayoutParams.MATCH_PARENT,
     400
    )
    dialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL)
  }

  private fun setupClickListeners(view: View) {
    val button = view.findViewById<Button>(R.id.button)
    button.setOnClickListener {
      dismiss()
    }
  }

}