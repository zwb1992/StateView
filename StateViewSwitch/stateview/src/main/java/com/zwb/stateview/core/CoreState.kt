package com.zwb.stateview.core

import android.util.Log
import android.view.View
import com.zwb.stateview.base.BaseState


/**
 * 核心状态，用于显示dataView
 */
class CoreState(dataView: View?) : BaseState(dataView) {

    companion object {
        const val stateName = "CoreState"
    }

    /**
     * 核心状态不需要布局文件
     */
    override fun tellMeLayoutId() = 0

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        Log.e("CoreState","=====onViewCreated====")
    }

    override fun onResume() {
        super.onResume()
        Log.e("CoreState","=====onResume====")
    }

    override fun onPause() {
        super.onPause()
        Log.e("CoreState","=====onPause====")
    }
}