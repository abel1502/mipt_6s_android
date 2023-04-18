package com.abel.mipt_6s_test_app.data

import android.util.Log


object NavParamsCache {
    private val cache = mutableMapOf<Int, Any>()
    private var next = 1  // 0 is reserved to signal errors by jetpack navigation

    @Suppress("UNCHECKED_CAST")
    fun <T> peek(key: Int): T? {
        return cache[key] as? T
    }

    fun <T> take(key: Int): T? {
        val value = peek<T>(key)
        drop(key)
        return value
    }

    fun <T> put(value: T): Int {
        val key = next++
        cache[key] = value as Any

        return key
    }

    // Note: without this, data will leak
    fun drop(key: Int) {
        cache.remove(key)
    }
}