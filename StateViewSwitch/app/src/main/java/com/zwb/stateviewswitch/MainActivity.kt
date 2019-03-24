package com.zwb.stateviewswitch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zwb.stateview.core.CoreState
import com.zwb.stateview.event.OnStateEventListener
import com.zwb.stateview.manage.StateViewManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnStateEventListener {
    var stateViewManager: StateViewManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stateViewManager = StateViewManager.Builder()
                .dataView(tvData)
                .stateEventLListener(this)
                .build()

        btLoading.setOnClickListener {
            stateViewManager?.switchState(LoadingState.stateName)
        }

        btEmpty.setOnClickListener {
            stateViewManager?.switchState(EmptyState.stateName)
        }

        btError.setOnClickListener {
            stateViewManager?.switchState(ErrorState.stateName)
        }

        btData.setOnClickListener {
            stateViewManager?.switchState(CoreState.stateName)
        }
    }

    override fun onClick(eventName: String, view: View) {
        when (eventName) {
            ErrorState.event_retry -> stateViewManager?.switchState(LoadingState.stateName)
        }
    }
}
