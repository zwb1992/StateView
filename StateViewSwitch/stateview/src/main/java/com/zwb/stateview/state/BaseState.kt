package com.zwb.stateview.state

import android.view.View
import com.zwb.stateview.event.OnStateEventListener

abstract class BaseState() : IState {
    var onStateEventListener: OnStateEventListener? = null

    constructor(view: View?) : this() {
        contentView = view
    }

    var contentView: View? = null

    override fun setStateEventListener(onStateEventListener: OnStateEventListener?) {
        this.onStateEventListener = onStateEventListener
    }

    override fun onViewCreated(view: View) {
        contentView = view
    }

    override fun getContext() = contentView?.context

    override fun getView() = contentView

    override fun onPause() {

    }

    override fun onResume() {

    }
}