package ru.gbpractice.dilib

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

object DiTool {
    private val depMap = HashMap<Qualifier, Any>()

    fun <T : Any> add(qualifier: Qualifier, dependency: T) {
        depMap[qualifier] = dependency
    }

    inline fun <reified T : Any> add(dependency: T) {
        add(Qualifier(T::class), dependency)
    }

    inline fun <reified T : Any> add(qualifierName: String, dependency: T) {
        add(Qualifier(T::class, qualifierName), dependency)
    }

    data class Qualifier(
        private val clazz: KClass<*>,
        private val qualifierName: String = "Default"

    )

    fun <T : Any> get(qualifier: Qualifier): T {
        if (depMap.containsKey(qualifier)) {
            return depMap[qualifier] as T
        } else throw IllegalArgumentException("No dependency available to provide from map")
    }

    inline fun <reified T : Any> inject(): T {
        return get(Qualifier(T::class))
    }

    inline fun <reified T : Any> inject(qualifierName: String): T {
        return get(Qualifier(T::class, qualifierName))
    }
}

