package ru.gbpractice.dilib

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

object DiTool {
    private val depMap = HashMap<KClass<*>, Any>()

    fun <T : Any> add(clazz: KClass<T>, dependency: T) {
        depMap[clazz] = dependency
    }

    inline fun <reified T : Any> add(dependency: T) {
        add(T::class, dependency)
    }

    fun <T : Any> get(clazz: KClass<T>): T {
        if (depMap.containsKey(clazz)) {
            return depMap[clazz] as T
        } else throw IllegalArgumentException("No dependency available to provide from map")
    }

    inline fun <reified T: Any> inject(): T {
        return get(T::class)
    }


}

