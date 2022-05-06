package net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract

interface GlobalEventNotifierContract<T> {
    fun addListener(eventListener: EventListener<T>)
    interface EventListener<T> {
        fun onEvent(event: T)
    }
}