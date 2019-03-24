package com.zwb.stateview.event

import android.view.View

interface OnStateEventListener {

    fun onClick(eventName: String, view: View)
}