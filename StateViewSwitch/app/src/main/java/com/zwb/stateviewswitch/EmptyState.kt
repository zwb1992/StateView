package com.zwb.stateviewswitch

import android.util.Log
import android.view.View
import com.zwb.stateview.state.BaseState

class EmptyState : BaseState() {

    companion object {
        const val stateName = "EmptyState"
    }


    override fun tellMeLayoutId() = R.layout.state_empty

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        Log.e("EmptyState","=====onViewCreated====")
    }

    override fun onResume() {
        super.onResume()
        Log.e("EmptyState","=====onResume====")
    }

    override fun onPause() {
        super.onPause()
        Log.e("EmptyState","=====onPause====")
    }
}