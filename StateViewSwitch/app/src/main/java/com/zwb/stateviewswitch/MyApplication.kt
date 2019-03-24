package com.zwb.stateviewswitch

import android.app.Application
import com.zwb.stateview.repository.StateRepositoryFactory

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        StateRepositoryFactory.registerState(LoadingState.stateName, LoadingState::class.java)
        StateRepositoryFactory.registerState(EmptyState.stateName, EmptyState::class.java)
        StateRepositoryFactory.registerState(ErrorState.stateName, ErrorState::class.java)
    }
}