package com.zwb.stateview.manager

import android.view.View
import com.zwb.stateview.state.IState
import com.zwb.stateview.state.CoreState
import com.zwb.stateview.event.OnStateEventListener
import com.zwb.stateview.repository.StateRepository
import com.zwb.stateview.repository.StateRepositoryFactory

class StateViewManager private constructor() {

    private constructor(builder: Builder) : this() {
        stateViewHelper = builder.stateViewHelper
        stateRepository = builder.stateRepository
        stateEventListener = builder.stateEventListener
    }


    private var stateViewHelper: StateViewHelper? = null
    private var stateRepository: StateRepository? = null
    private var stateEventListener: OnStateEventListener? = null

    class Builder {
        internal val stateRepository = StateRepository()
        private var dataView: View? = null
        internal var stateViewHelper: StateViewHelper? = null
        internal var stateEventListener: OnStateEventListener? = null
        fun dataView(dataView: View): Builder {
            this.dataView = dataView
            return this
        }

        fun addState(stateName: String, state: IState): Builder {
            stateRepository.addState(stateName, state)
            return this
        }

        fun stateEventLListener(stateEventListener: OnStateEventListener?): Builder {
            this.stateEventListener = stateEventListener
            return this
        }

        fun build(): StateViewManager {
            val coreState = CoreState(dataView)
            stateRepository.addState(CoreState.stateName, coreState)
            stateViewHelper = StateViewHelper(dataView!!, coreState)
            return StateViewManager(this)
        }
    }

    fun switchState(stateName: String): Boolean {
        try {
            var state = stateRepository?.getState(stateName)
            if (state != null) {
                state.setStateEventListener(stateEventListener)
                return stateViewHelper!!.switchState(state)
            }
            val clazz = StateRepositoryFactory.getState(stateName)
            if (clazz != null) {
                state = clazz.newInstance()
                state.setStateEventListener(stateEventListener)
                stateRepository?.addState(stateName, state)
                return stateViewHelper!!.switchState(state)
            } else {
                throw RuntimeException("no state named $stateName found ")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}