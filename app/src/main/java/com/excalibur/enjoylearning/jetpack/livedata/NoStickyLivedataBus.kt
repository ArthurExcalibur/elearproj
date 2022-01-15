package com.excalibur.enjoylearning.jetpack.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field
import java.lang.reflect.Method

object NoStickyLivedataBus {

    // 存放订阅者
    private val bus : MutableMap<String, NoStickyLivedata<Any>> by lazy { HashMap() }

    // 暴露一个函数，给外界注册 订阅者关系
    @Synchronized
    fun <T> with(key: String, type: Class<T>, isStick: Boolean = true) : NoStickyLivedata<T> {
        if (!bus.containsKey(key)) {
            bus[key] = NoStickyLivedata(isStick)
        }
        return bus[key] as NoStickyLivedata<T>
    }

    class NoStickyLivedata<T> private constructor() : MutableLiveData<T>() {

        private var isSticky: Boolean = false

        constructor(isSticky: Boolean): this(){
            this.isSticky = isSticky
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            if(!isSticky){
                hook(observer)
            }
        }

        private fun hook(observer: Observer<in T>){
            val mObserversFiled: Field = LiveData::class.java.getDeclaredField("mObservers")
            mObserversFiled.isAccessible = true
            val mObservers = mObserversFiled.get(this)

            //TODO SafeIterableMap的get方法返回的是Entry<K, V>
            val getMethod: Method = mObservers.javaClass.getDeclaredMethod("get", Any::class.java)
            getMethod.isAccessible = true
            val observerEntity = getMethod.invoke(mObservers, observer)

            var observerWrapper: Any? = null
            if(observerEntity != null && observerEntity is Map.Entry<*, *>){
                observerWrapper = observerEntity.value
            }

            //TODO 获取mVersion
            val mVersionFiled: Field = LiveData::class.java.getDeclaredField("mVersion")
            mVersionFiled.isAccessible = true
            val mVersion = mVersionFiled.get(this)

            //TODO mLastVersion=mVersion
            observerWrapper?.let {
                /**
                 * observerWrapper类型是LifecycleBoundObserver，他继承自ObserverWrapper（里面包含mLastVersion字段）
                 * 所以用it.javaClass.superclass
                 */
                val mLastVersionField = it.javaClass.superclass.getDeclaredField("mLastVersion")
                mLastVersionField.isAccessible = true
                mLastVersionField.set(it, mVersion)
            }
        }

    }

}