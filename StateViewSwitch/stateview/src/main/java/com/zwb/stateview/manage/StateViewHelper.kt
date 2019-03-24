package com.zwb.stateview.manage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zwb.stateview.base.IState

/**
 * 对view的添加，显示进行处理
 */
class StateViewHelper private constructor() {

    private var dataView: View? = null
    private var parentView: ViewGroup? = null
    private var params: ViewGroup.LayoutParams? = null
    private var curState: IState? = null
    private var mViewIndex: Int = 0


    constructor(dataView: View, state: IState) : this() {
        this.dataView = dataView
        this.curState = state
        params = dataView.layoutParams
        parentView = dataView.parent as ViewGroup
        if (parentView == null) {
            parentView = dataView.rootView.findViewById(android.R.id.content) as ViewGroup
        }
        /*记录要显示的View在父View中的位置*/
        val childCount = parentView!!.childCount
        for (index in 0 until childCount) {
            if (dataView === parentView!!.getChildAt(index)) {
                mViewIndex = index
                break
            }
        }
    }

    fun switchState(state: IState): Boolean {
        try {
            if (curState == state) {
                return false
            }
            if (state.getView() == null) {
                val idRes = state.tellMeLayoutId()
                if (idRes <= 0) {
                    return false
                }
                val view = LayoutInflater.from(dataView!!.context).inflate(idRes, null)
                state.onViewCreated(view)
            }
            val view = state.getView()
            if (parentView!!.indexOfChild(view) != -1 && view!!.parent != null) {
                curState!!.getView()?.visibility = View.GONE
                view.visibility = View.VISIBLE
                curState?.onPause()
                state.onResume()
            } else {
                parentView?.addView(view, mViewIndex, params)
                curState?.getView()?.visibility = View.GONE
                curState?.onPause()
                state.onResume()
            }
            curState = state
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }
}