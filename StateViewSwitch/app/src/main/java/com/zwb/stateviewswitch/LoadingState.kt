package com.zwb.stateviewswitch

import android.util.Log
import android.view.View
import com.zwb.stateview.base.BaseState

class LoadingState : BaseState() {

    companion object {
        const val stateName = "LoadingState"
    }


    override fun tellMeLayoutId() = R.layout.state_loading

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        Log.e("LoadingState","=====onViewCreated====")
    }

    override fun onResume() {
        super.onResume()
        Log.e("LoadingState","=====onResume====")
    }

    override fun onPause() {
        super.onPause()
        Log.e("LoadingState","=====onPause====")
    }

}