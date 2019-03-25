# StateView
状态view切换  Loading  Error  Empty   data  动态切换   可自定义状态view

自定义状态类：
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

class ErrorState : BaseState() {

    companion object {
        const val stateName = "ErrorState"
        const val event_retry = "ErrorState_event_retry"
    }


    override fun tellMeLayoutId() = R.layout.state_error


    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        view.findViewById<Button>(R.id.btRetry).setOnClickListener {
            onStateEventListener?.onClick(event_retry, it)
        }
        Log.e("ErrorState", "=====onViewCreated====")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ErrorState", "=====onResume====")
    }

    override fun onPause() {
        super.onPause()
        Log.e("ErrorState", "=====onPause====")
    }

}

注册状态：
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        StateRepositoryFactory.registerState(LoadingState.stateName, LoadingState::class.java)
        StateRepositoryFactory.registerState(EmptyState.stateName, EmptyState::class.java)
        StateRepositoryFactory.registerState(ErrorState.stateName, ErrorState::class.java)
    }
}

使用状态：

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