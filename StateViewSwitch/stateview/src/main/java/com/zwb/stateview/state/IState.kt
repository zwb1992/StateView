package com.zwb.stateview.state

import android.content.Context
import android.view.View
import com.zwb.stateview.event.OnStateEventListener

interface IState {

    fun getView(): View?

    fun tellMeLayoutId(): Int

    fun getContext(): Context?

    fun onViewCreated(view: View)

    fun onPause()

    fun onResume()

    fun setStateEventListener(onStateEventListener: OnStateEventListener?)
}