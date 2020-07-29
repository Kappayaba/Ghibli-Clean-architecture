package com.starscience.cuisinietmoi.cuisineetmoi.cache

import android.content.Context

class PreferencesHelper(val context: Context) {
    private val PREF_NAME = "com.starcience.preferences"  // Name of the shared pref domain
    private val PREF_KEY = "last_cache"                   // Key name to get the last cached value

    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var lastCachedTime : Long
    get() = sharedPref.getLong(PREF_KEY, 0)
    set( cacheTime : Long) = sharedPref.edit().putLong(PREF_KEY, cacheTime).apply()
}