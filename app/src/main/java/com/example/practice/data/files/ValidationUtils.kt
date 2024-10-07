package com.example.practice.data.files

import android.text.TextUtils
import android.util.Patterns

object ValidationUtils {
    fun isTextNotEmpty(text: String?):Boolean {
        return !TextUtils.isEmpty(text)
    }

    fun isTextNotFill(int: String) : Boolean {
        return !TextUtils.isEmpty(int.toString())
    }

    fun isValidEmail(text:String) : Boolean {
        return if (TextUtils.isEmpty(text)) false
        else return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}