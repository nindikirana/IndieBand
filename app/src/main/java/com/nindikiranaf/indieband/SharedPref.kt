package com.nindikiranaf.indieband

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private val read: SharedPreferences =
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    private val write: SharedPreferences.Editor = read.edit()

    fun setData(key: String, value: Any?) {
        when (value) {
            is Boolean -> write.putBoolean(key, value)
            is Float -> write.putFloat(key, value)
            is Int -> write.putInt(key, value)
            is Long -> write.putLong(key, value)
            is String -> write.putString(key, value)
        }
        write.commit()
    }

    fun getBoolean(key: String, default: Boolean = false) = read.getBoolean(key, default)
    fun getFloat(key: String, default: Float = 0f) = read.getFloat(key, default)
    fun getInt(key: String, default: Int = 0) = read.getInt(key, default)
    fun getLong(key: String, default: Long = 0) = read.getLong(key, default)
    fun getString(key: String, default: String? = null) = read.getString(key, default)

    companion object {
        const val GRID_LAYOUT = "grid_layout"
        const val GENRE = "genre"
        const val ASAL = "asal"
        const val TH_AKTIF = "th_aktif"
        const val LAGU_FAV = "lagu_fav"
        const val APP_NAME = "app_name"
        const val COLUMN = "column"
    }

    var gridLayout: Boolean
        set(value) = setData(GRID_LAYOUT, value)
        get() = getBoolean(GRID_LAYOUT)

    var genre: Boolean
        set(value) = setData(GENRE, value)
        get() = getBoolean(GENRE)

    var asal: Boolean
        set(value) = setData(ASAL, value)
        get() = getBoolean(ASAL)

    var th_aktif: Boolean
        set(value) = setData(TH_AKTIF, value)
        get() = getBoolean(TH_AKTIF)

    var lagu_fav: Boolean
        set(value) = setData(LAGU_FAV, value)
        get() = getBoolean(LAGU_FAV)

    var appName: String?
        set(value) = setData(APP_NAME, value)
        get() = getString(APP_NAME)

    var column: Int
        set(value) = setData(COLUMN, value)
        get() = getInt(COLUMN)

}