package com.example.mathgame.core.cache

import android.content.Context
import android.content.SharedPreferences

class AppCache private constructor(context: Context){


    init {
        preferences = context.getSharedPreferences("puzzle_15", Context.MODE_PRIVATE)
    }

    fun saveTime(timeCount: Int?) {
        preferences!!.edit().putInt("TIME_COUNT", timeCount!!).apply()
    }

    fun saveStep(stepCount: Int?) {
        preferences!!.edit().putInt("MOVE_COUNT", stepCount!!).apply()
    }

    fun saveProrety(son: Int?){
        preferences!!.edit().putInt("PRORETY", son!!).apply()
    }



    companion object {
        private var `object`: AppCache? = null
        private var preferences: SharedPreferences? = null
        fun init(context: Context) {
            if (`object` == null) {
                `object` = AppCache(context)
            }
        }

        fun getObject() = `object`

    }


    fun getTime() = preferences!!.getInt("TIME_COUNT", 0)
    fun getMoves() = preferences!!.getInt("MOVE_COUNT", 0)


    fun saveTheme(son: Int) {
        preferences!!.edit().putInt("KEY_THEME", son).apply()
    }

    fun getTheme(): Int {
        return preferences!!.getInt("KEY_THEME", 0)
    }
    fun getProrety():Int{
        return preferences!!.getInt("PRORETY", 0)
    }

}