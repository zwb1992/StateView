package com.zwb.stateviewswitch

import android.util.Log
import android.view.View
import android.widget.Button
import com.zwb.stateview.state.BaseState

class ErrorState : BaseState() {

    companion object {
        const val stateName = "ErrorState"
        const val event_retry = "ErrorState_event_retry"
    }


    override fun tellMeLayoutId() = R.layout.state_error


    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        view.findViewById<Button>(R.id.btRetry).setOnClickListener {
            onStateEventListener?.onClick(event_retry, it)
        }
        Log.e("ErrorState", "=====onViewCreated====")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ErrorState", "=====onResume====")
    }

    override fun onPause() {
        super.onPause()
        Log.e("ErrorState", "=====onPause====")
    }

}