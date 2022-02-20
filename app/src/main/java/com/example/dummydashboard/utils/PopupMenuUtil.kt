package com.example.dummydashboard.utils

import android.widget.PopupMenu

fun PopupMenu.setupIcons() : Boolean {
    return try {
        val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
        fieldMPopup.isAccessible = true
        val mPopup = fieldMPopup.get(this)
        mPopup.javaClass
            .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(mPopup, true)
        true
    } catch (error: Exception) {
        println(error.stackTrace)
        false
    } finally {
        this.show()
    }
}