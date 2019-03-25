package com.zwb.stateview.repository

import com.zwb.stateview.state.BaseState
import com.zwb.stateview.state.IState


/**
 * 为每一个模块（界面）单独创建的状态仓库
 */
class StateRepository {

    private var stateMap: MutableMap<String, IState> = mutableMapOf()

    /**
     * 添加一个状态
     * @param stateName 状态的名称
     * @param state 状态的实例
     */
    fun addState(stateName: String, state: IState) {
        stateMap[stateName] = state
    }


    /**
     * 添加多个状态
     * @param states 状态的集合
     * @param state 状态的实例
     */
    fun addState(states: MutableMap<String, BaseState>) {
        for ((key,value) in states){
            stateMap[key] = value
        }
    }


    /**
     * 按照状态名称获取一个状态的实例，若未添加过，返回null
     * @param stateName 状态的名称
     */
    fun getState(stateName: String): IState? = stateMap[stateName]
}