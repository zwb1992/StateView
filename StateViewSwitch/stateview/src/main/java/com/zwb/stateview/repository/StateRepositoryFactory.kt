package com.zwb.stateview.repository

import android.util.Log
import com.zwb.stateview.state.IState

/**
 * 状态仓库，使用之前先注册，推荐房子Application中注册需要的状态view
 */
object StateRepositoryFactory {

    private var stateMap: MutableMap<String, Class<out IState>> = mutableMapOf()


    /**
     * 注册一个状态
     * @param stateName 状态的名称
     * @param clazz 状态的类型
     */
    fun registerState(stateName: String, clazz: Class<out IState>) {
        Log.e("info","registerState  stateName = $stateName  clazz = $clazz")
        stateMap[stateName] = clazz
    }

    /**
     * 清除一个状态
     * @param stateName 状态的名称
     */
    fun unRegisterState(stateName: String) {
        stateMap.remove(stateName)
    }

    /**
     * 清除所有状态
     */
    fun unRegisterState() {
        stateMap.clear()
    }

    /**
     * 按照状态名称获取一个状态的类型，若未注册过，返回null
     * @param stateName 状态的名称
     */
    fun getState(stateName: String): Class<out IState>? = stateMap[stateName]

}